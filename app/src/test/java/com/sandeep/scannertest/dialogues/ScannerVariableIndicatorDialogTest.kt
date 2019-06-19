package com.sandeep.scannertest.dialogues

import com.sandeep.scannertest.database.valueobjects.VariableVo
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ScannerVariableIndicatorDialogTest {

    lateinit var variableVo : VariableVo

    @Before
    fun createVariableVo() {
        variableVo = VariableVo()
        variableVo.minValue =1
        variableVo.maxValue =99
    }

    @Test fun inputValidator_CorrectInputRight_ReturnsTrue() {
        assertTrue(StudtyTypeValueValidator.isValidInput(variableVo,"2"))
    }

    @Test fun inputValidator_CorrectInputMin_ReturnsTrue() {
        assertTrue(StudtyTypeValueValidator.isValidInput(variableVo,"0"))
    }
    @Test fun inputValidator_CorrectInputMax_ReturnsTrue() {
        assertTrue(StudtyTypeValueValidator.isValidInput(variableVo,"100"))
    }
}