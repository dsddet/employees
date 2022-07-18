package com.dsddet.employees.controller;

import com.dsddet.employees.domain.Employee;
import com.dsddet.employees.exception.EmployeeNotFoundException;
import com.dsddet.employees.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest{

    @MockBean
    private IEmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private Employee employee;

    @Before
    public void setUp(){
        this.employee=Employee.builder().id(1L).firstName("Mary")
                .lastName("Jane").department("IT").email("mj@work.com")
                .jobTitle("Dev").startDate(Date.from(Instant.now()))
                .build();

    }

    @Test
    public void testGetEmployee() throws Exception{
        given(employeeService.getEmployeeById(1L)).willReturn(this.employee);

        mockMvc.perform(get("/employee/{id}",1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(1)));
    }

    @Test
    public void test_getting_inexistent_employee() throws Exception{
        given(employeeService.getEmployeeById(anyLong())).willReturn(null);

        mockMvc.perform(get("/employee/{id}",5L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_post_new_employee() throws Exception{
        given(employeeService.saveEmployee(this.employee)).willReturn(this.employee);

        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(this.employee)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(1))).andReturn();

    }

    @Test
    public void test_post_invalid_employee() throws Exception{

        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(Employee.builder().build())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void test_update_existing_employee() throws Exception{
        given(employeeService.updateEmployee(this.employee)).willReturn(this.employee);

        mockMvc.perform(patch("/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(this.employee)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(1)))
                .andReturn();

    }

    @Test
    public void test_update_unknown_employee() throws Exception{
        given(employeeService.updateEmployee(any(Employee.class))).willThrow(new EmployeeNotFoundException("Inexistent user"));

        mockMvc.perform(patch("/employee",1l).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(this.employee)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void test_delete_employee() throws Exception{

        mockMvc.perform(delete("/employee/{id}",1l).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }



}
