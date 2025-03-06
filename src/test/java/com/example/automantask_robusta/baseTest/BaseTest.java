package com.example.automantask_robusta.baseTest;

import base.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeClass
    public void setUpBrowser()  {
        DriverManager.setupBrowser();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeBrowser();
    }
}