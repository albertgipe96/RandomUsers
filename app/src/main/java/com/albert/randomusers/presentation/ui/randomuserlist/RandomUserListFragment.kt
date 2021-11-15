package com.albert.randomusers.presentation.ui.randomuserlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albert.randomusers.R
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.presentation.ui.randomuserdetail.RandomUserDetailFragment
import com.albert.randomusers.presentation.ui.randomuserlist.adapter.RandomUserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RandomUserListFragment : Fragment() {

    companion object {
        fun newInstance() = RandomUserListFragment()
    }

    private val viewModel: RandomUserListViewModel by viewModels()

    private var rvRandomUserList: RecyclerView? = null
    private var rvAdapter: RandomUserListAdapter? = null
    private var deletedUsers = 0

    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRandomUsersList(page)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setCircularProgressBarObservers()
        setLoadingErrorObserver()
        initRecyclerView()
        initEditText()
    }

    private fun initRecyclerView() {
        rvRandomUserList = view?.findViewById(R.id.rv_random_user_list)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvRandomUserList?.layoutManager = layoutManager
        rvAdapter = RandomUserListAdapter(mutableListOf(), object : RandomUserListAdapter.OnItemClickListener {
            override fun onItemClick(item: RandomUserUIModel) {
                showDetailFragment(item)
            }

            override fun onItemDelete(item: RandomUserUIModel) {
                deletedUsers += 1
                rvRandomUserList?.recycledViewPool?.clear()
                rvAdapter?.notifyDataSetChanged()
                addToDeletedList(item)
            }
        })
        rvRandomUserList?.adapter = rvAdapter

        // Update UI with LiveData
        viewModel.randomUserList.observe(viewLifecycleOwner, {
            rvAdapter?.setRandomUsers(it)
        })

        // When last position reached, load more data
        rvRandomUserList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastCompletelyVisibleItemPosition = (rvRandomUserList?.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == (rvAdapter?.itemCount ?:0 - deletedUsers) - 1) {
                    page += 1
                    viewModel.getRandomUsersList(page)
                }
            }
        })
    }

    private fun initEditText() {
        val etFilterList: EditText? = view?.findViewById(R.id.et_filter_list)
        etFilterList?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchUser(etFilterList?.text.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun showDetailFragment(randomUser: RandomUserUIModel) {
        val fragment = RandomUserDetailFragment.newInstance(randomUser)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.add(R.id.container, fragment)
        transaction?.addToBackStack(null)?.commit()
    }

    private fun setCircularProgressBarObservers() {
        val circularProgressBarTop: ProgressBar? = view?.findViewById(R.id.top_circular_progress_bar)
        viewModel.loadingRandomUserListFirstCall.observe(viewLifecycleOwner, {
            if (!it) {
                rvRandomUserList?.visibility = View.VISIBLE
                circularProgressBarTop?.visibility = View.GONE
            }
        })

        val circularProgressBarBottom: ProgressBar? = view?.findViewById(R.id.bottom_circular_progress_bar)
        viewModel.loadingRandomUserListOtherCalls.observe(viewLifecycleOwner, {
            if (it) {
                circularProgressBarBottom?.visibility = View.VISIBLE
            } else {
                circularProgressBarBottom?.visibility = View.GONE
            }
        })
    }

    private fun setLoadingErrorObserver() {
        val tvErrorServiceCall: TextView? = view?.findViewById(R.id.tv_error_service_call)
        viewModel.loadingError.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                tvErrorServiceCall?.text = it
                tvErrorServiceCall?.visibility = View.VISIBLE
            } else {
                tvErrorServiceCall?.visibility = View.GONE
            }
        })
    }

    private fun addToDeletedList(user: RandomUserUIModel) {
        viewModel.deleteUserFromList(user)
    }

}