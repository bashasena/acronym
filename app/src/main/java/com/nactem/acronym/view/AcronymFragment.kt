package com.nactem.acronym.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nactem.acronym.databinding.FragmentFirstBinding
import com.nactem.acronym.model.LoadingState
import com.nactem.acronym.viewmodel.AcronymViewModel

/**
 *  Acronym Fragment
 */
class AcronymFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: AcronymViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val acronymListAdapter = AcronymListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initializeUI()
        initializeOberserver()
    }

    private fun initializeOberserver() {
        viewModel.loadingStateLiveData.observe(requireActivity()) { state ->
            onLoadingStateChanged(state)
        }

        viewModel.acronymLiveData.observe(requireActivity()) { listAcronym ->
            acronymListAdapter.updateAcronymList(listAcronym.get(0).lfs)
        }
    }

    private fun onLoadingStateChanged(state: LoadingState?) {

        binding.acronymRV.visibility = if(state == LoadingState.LOADED) View.VISIBLE else View.GONE

        binding. errorTV.visibility = if(state == LoadingState.ERROR) View.VISIBLE else View.GONE

        binding.loadingPB.visibility = if(state == LoadingState.LOADING) View.VISIBLE else View.GONE
    }

    private fun initializeUI() {
        binding.acronymRV.adapter = acronymListAdapter
        binding.acronymRV.layoutManager = LinearLayoutManager(requireContext())

        binding.btnFind.setOnClickListener {
            viewModel.getAbbreviation(binding.searchAbreviation.text.toString())
         }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}