package com.example.mbiletask.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mbiletask.R
import com.example.mbiletask.data.Status
import com.example.mbiletask.data.remote.model.Record
import com.example.mbiletask.data.remote.model.RecordYear
import com.example.mbiletask.ui.adapter.RecordsAdapter
import com.example.mbiletask.utils.Constants
import com.example.mbiletask.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }
    private lateinit var recordsAdapter: RecordsAdapter
    private var isDataLoaded = false

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeOnRefresh()
        initRecyclerViews()
        getRecordsData()
        observeOnRecordsData()
    }

    private fun observeOnRefresh() {
        refresh_view.setOnRefreshListener {
            getRecordsData()
        }
    }

    private fun initRecyclerViews() {
        recordsAdapter = RecordsAdapter()
        records_rv.layoutManager = GridLayoutManager(this, Constants.RECORDS_SPAN_COUNT)
        records_rv.adapter = recordsAdapter
    }

    private fun getRecordsData() {
        isDataLoaded = false
        showLoader()
        homeViewModel.getRecordsData()
    }

    private fun observeOnRecordsData() {
        homeViewModel.recordsLiveData.observe(this, Observer { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoader()
                }
                Status.ERROR -> {
                    hideLoader()
                }
                Status.SUCCESS -> {
                    hideLoader()
                    if (response.data?.records.isNullOrEmpty()) {
                        showNoInternetViews()
                    } else if (!isDataLoaded) {
                        setRecordsData(response.data)
                    }
                }
            }
        })
    }

    private fun setRecordsData(data: RecordsPresentation?) {
        isDataLoaded = true
        recordsAdapter.setRecordsList(handleDisplay(ArrayList(data!!.records)))
    }

    private fun showNoInternetViews() {
        no_date_view.visibility = View.VISIBLE
        no_internet_container.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        progress_bar.visibility = View.GONE
        no_date_view.visibility = View.GONE
        refresh_view.isRefreshing = false
    }

    private fun showLoader() {
        progress_bar.visibility = View.VISIBLE
        no_date_view.visibility = View.VISIBLE
        no_internet_container.visibility = View.GONE

    }

    private fun handleDisplay(recordDto: List<Record>): ArrayList<RecordYear> {
        var startPoint: String = "2008-Q1"
        var endPoint: String = "2019-Q1"
        var started: Boolean = false
        var ended: Boolean = false
        var counter = 0
        var sumYear = 0.0
        var previousRecordValue = 0.0
        var recordYear: RecordYear = RecordYear(quarters = ArrayList<Record>(), avg = 0.0)
        var yearRecords: ArrayList<RecordYear> = ArrayList<RecordYear>()
        if (recordDto != null && recordDto.size > 0) {
            for (record in recordDto) {
                if (record.quarter == startPoint)
                    started = true
                if (record.quarter == endPoint)
                    ended = true
                if (record.volume_of_mobile_data.toDouble() < previousRecordValue)
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
                        sumYear += record.volume_of_mobile_data.toDouble()
                    }
                }
                previousRecordValue = record.volume_of_mobile_data.toDouble()
            }
        }
        return yearRecords;
    }
}
