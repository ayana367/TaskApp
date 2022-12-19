package com.example.taskapp.ui.OnBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.databinding.FragmentOnBoardBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator


class OnBoardFragment : Fragment() {

    private var binding: FragmentOnBoardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(LayoutInflater.from(context),container,false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter(childFragmentManager,this::onSkipClick,this::onNextClick)
        adapter.also { it.also { binding!!.vpBoard.adapter = it } }

       val dotsIndicator : SpringDotsIndicator = binding!!.springDotsIndicator
        dotsIndicator.attachTo(binding!!.vpBoard)
    }
    private fun onSkipClick(){
        binding!!.vpBoard.setCurrentItem(initClick(-1),true)
    }
    private fun onNextClick(){
        binding!!.vpBoard.setCurrentItem(initClick(+1),true)
    }
    private fun initClick(i:Int): Int {
        return binding!!.vpBoard.currentItem +i
    }
}



