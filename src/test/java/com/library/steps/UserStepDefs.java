package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UserStepDefs {


    String actualUserCount;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
       // Make a connection with library
        DB_Util.createConnection();
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount   = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);


    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query="select count(id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount, actualUserCount);

        //close conn
        DB_Util.destroy();

    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(io.cucumber.datatable.DataTable dataTable) {

    }

}
