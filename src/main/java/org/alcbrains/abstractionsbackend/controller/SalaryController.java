package org.alcbrains.abstractionsbackend.controller;

import org.alcbrains.abstractionsbackend.domain.entity.Salary;
import org.alcbrains.abstractionsbackend.domain.entity.SalaryId;
import org.alcbrains.abstractionsbackend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(@Autowired SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("")
    public List<Salary> getAllSalaries() {
        return salaryService.findAllSalaries();
    }

    @GetMapping("/salary/{employeeId}")
    public Salary getSalaryOfEmployee(@PathVariable int employeeId, @RequestParam String fromDate) throws EntityNotFoundException {
        return salaryService.findSalaryById(employeeId, fromDate).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/salary")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSalary(@RequestBody Salary salary) {

        salaryService.createSalary(salary);
        return "Successfully created Salary";
    }

}
