package com.target.targetcasestudy.ui.adapter.base

interface Visitable {
    fun type(typeFactory: TargetTypesFactory): Int
}