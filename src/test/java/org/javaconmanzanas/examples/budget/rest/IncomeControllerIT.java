package org.javaconmanzanas.examples.budget.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class IncomeControllerIT {

    private static final String BUDGET_NAME = "November 2017";
    public static final long BUDGET_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveIncome() throws Exception {
        final String budgetRequestBody = "{\"name\":\"November 2017\"}";
        mockMvc.perform(post("/budgets")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(budgetRequestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(BUDGET_ID))
                .andExpect(jsonPath("$.name").value(BUDGET_NAME));

        final String incomeRequestBody = "{\"idBudget\": 1, \"name\":\"Salary\", \"amount\":3000.00}";
        mockMvc.perform(post("/incomes")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(incomeRequestBody))
                .andExpect(status().isCreated());
    }
}
