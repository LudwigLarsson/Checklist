package ru.samsung.case2022.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.samsung.case2022.databinding.FragmentMainBinding
import ru.samsung.case2022.ui.core.BaseViewBindingFragment


class MainFragment : BaseViewBindingFragment<FragmentMainBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToRoot: Boolean,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, attachedToRoot)
}