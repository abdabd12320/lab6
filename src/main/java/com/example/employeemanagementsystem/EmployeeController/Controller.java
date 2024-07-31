package com.example.employeemanagementsystem.EmployeeController;

import com.example.employeemanagementsystem.ApiResponse.Api;
import com.example.employeemanagementsystem.EmployeeModel.Model;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v/employee")
public class Controller {

    ArrayList<Model> models = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEmployee()
    {
        return ResponseEntity.status(200).body(models);
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Model model, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        models.add(model);
        return ResponseEntity.status(200).body(new Api("employee added"));
    }
    @PutMapping("/update/{i}")
    public ResponseEntity updateEmployee(@PathVariable int i,@Valid @RequestBody Model model,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        models.set(i,model);
        return ResponseEntity.status(200).body(new Api("employee added"));
    }
    @DeleteMapping("/delete/{i}")
    public ResponseEntity deleteEmployee(@PathVariable int i)
    {
        if(models.size() > i && i >= 0) {
            models.remove(i);
            return ResponseEntity.status(200).body(new Api("employee added"));
        }
        else return ResponseEntity.status(400).body(new Api("employee not found it"));
    }
    @GetMapping("/search/{p}")
    public ResponseEntity searchEmployee(@PathVariable String p)
    {
        ArrayList<Model> newModelArrayList = new ArrayList<>();
        for(Model model: models)
        {
            if(model.getPosition().equals(p))
                newModelArrayList.add(model);
        }
        return ResponseEntity.status(200).body(newModelArrayList);
    }
    @GetMapping("/get-by-range/{min}/{max}")
    public ResponseEntity getEmployeeByRange(@PathVariable int min,@PathVariable int max)
    {
        ArrayList<Model> newModelArrayList = new ArrayList<>();
        for(Model model: models)
        {
            if(model.getAge() >= min && model.getAge() <= max)
            {
                newModelArrayList.add(model);
            }
        }
        return ResponseEntity.status(200).body(newModelArrayList);
    }
    @PutMapping("/apply-annual-leave/{i}")
    public ResponseEntity applyAnnualLeave(@PathVariable int i)
    {
        if(models.size() > i && i >= 0) {
            models.get(i).setOnLeave(true);
            models.get(i).setAnnualLeave(models.get(i).getAnnualLeave()-1);
            return ResponseEntity.status(200).body(new Api("apply annual leave"));
        }
        else return ResponseEntity.status(400).body(new Api("employee not found it"));
    }
    @GetMapping("/get-employee-no-annual-leave")
    public ResponseEntity getEmployeeNoAnnualLeave()
    {
        ArrayList<Model> newModelArrayList = new ArrayList<>();
        for (Model model: models)
        {
            if(model.getAnnualLeave() == 0)
            {
                newModelArrayList.add(model);
            }
        }
        return ResponseEntity.status(200).body(newModelArrayList);
    }
    @PutMapping("/promote-employee/{i}/{j}")
    public ResponseEntity promateEmployee(@PathVariable int i,@PathVariable int j)
    {
        if(models.size() > i && i >= 0)
        {
            if(models.get(i).getPosition().equalsIgnoreCase("supervisor"))
            {
                if(models.get(j).getAge()>=30)
                {
                    if(models.get(j).isOnLeave())
                    {
                        models.get(j).setPosition("supervisor");
                        return ResponseEntity.status(200).body(new Api("promote employee"));
                    }
                    else return ResponseEntity.status(400).body(new Api("onLeave employee is false"));
                }
                else return ResponseEntity.status(400).body(new Api("age employee is not more than 29"));
            }
            else return ResponseEntity.status(400).body(new Api("position employee is not supervisor"));
        }
        else return ResponseEntity.status(400).body(new Api("id employee is not exists"));
    }

}
