package com.librarymanagementsystem.ctrl;

import com.librarymanagementsystem.model.Patron;
import com.librarymanagementsystem.service.GenericService;
import com.librarymanagementsystem.service.PatronService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patrons")
@RequiredArgsConstructor
@Tag(name = "Patrons Ctrl" , description = "The patrons management controller")
public class PatronCtrl extends GenericCtrl<Patron>{

    private final PatronService patronService;

    @Override
    public GenericService<Patron> getService() {
        return patronService;
    }
}
