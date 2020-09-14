package com.mostafa.assignment.ui.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.assignment.R
import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.model.RecordDto
import com.mostafa.assignment.domain.model.RecordYear
import com.mostafa.assignment.ui.home.adapter.HomeAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : DaggerFragment() {
    private val TAG: String = HomeFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf()) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            homeData.observe(this@HomeFragment, Observer {
                initView(it)
            }
            )
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })

        }
    }

    private fun initView(it: RecordDto?) {
        it?.let { it1 -> handleDisplay(it1) }
        rv_main_home.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_main_home.adapter = adapter
        progressBar_home.visibility = View.GONE
        if (it!!.results.isNotEmpty()) {
            adapter.clear()
            adapter.add(handleDisplay(it))

        } else {
            Toast.makeText(
                context,
                context?.getString(R.string.empty_list),
                android.widget.Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun handleDisplay(recordDto: RecordDto): ArrayList<RecordYear> {
        var startPoint: String = "2008-Q1"
        var endPoint: String = "2019-Q1"
        var started: Boolean = false
        var ended: Boolean = false
        var counter = 0
        var sumYear = 0.0
        var previousRecordValue = 0.0
        var recordYear: RecordYear = RecordYear(quarters = ArrayList<Record>(), avg = 0.0)
        var yearRecords: ArrayList<RecordYear> = ArrayList<RecordYear>()
        if (recordDto != null && recordDto.results.size > 0) {
            for (record in recordDto.results) {
                if (record.quarter == startPoint)
                    started = true
                if (record.quarter == endPoint)
                    ended = true
                if (record.volumeMobileData.toDouble() < previousRecordValue)
                    record.decrease = 1
                else
                    record.decrease = 0
                if (started && !ended) {
                    if (counter == 3) {
                        //calculate and add avg of year
                        recordYear.quarters.add(record)
                        recordYear.avg = sumYear / 4
                        yearRecords.add(recordYear)
                        recordYear = RecordYear(quarters = ArrayList<Record>(), avg = 0.0)
                        counter = 0
                        sumYear = 0.0
                    } else {
                        //continue sum
                        recordYear.quarters.add(record)
                        counter++
                        sumYear += record.volumeMobileData.toDouble()
                    }
                }
                previousRecordValue = record.volumeMobileData.toDouble()
            }
        }
        return yearRecords;
    }


}