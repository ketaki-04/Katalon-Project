import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper

Date date = new Date()

String datevalue = date.toString()

datevalue = datevalue.replace(' ', '')

datevalue = datevalue.replace(':', '')

println(datevalue)

//modifying
//GlobalVariable.EmpName = ('Test_Kk1' + datevalue)

GlobalVariable.EmpName = datevalue

println(GlobalVariable.EmpName)

response = WS.sendRequest(findTestObject('DummyEmp/CreateEmp'))

// Test case -- Get Id from response and pass it to get value
println(response.getResponseBodyContent())

String res = response.getResponseBodyContent()

JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(res)

println(parsedJson)

String fetchedid = parsedJson.id

println(fetchedid)

GlobalVariable.EmpId = fetchedid

 updateval = WS.sendRequest(findTestObject('DummyEmp/UpdateEmp',['EmpId': fetchedid]))
 
 println(updateval.getResponseBodyContent())
 
//Generic vrification of  string
assert WS.containsString(response, datevalue, false)

println(response.getResponseBodyContent())

//Generic verification of string
//WS.containsString(response, datevalue, false)
//Specific verification of element property
WS.verifyElementPropertyValue(response, 'name', GlobalVariable.EmpName)

//WS.verifyEqual(response.getResponseBodyContent().)
WS.verifyResponseStatusCode(response, 200)
