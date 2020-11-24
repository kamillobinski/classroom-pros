package com.example.demo.controller;

import com.example.demo.repository.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

@Controller
public class PlanController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private HourService hourService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    PdfService pdfService;

    @RequestMapping("/plan-{reqPlanId}")
    public String getRequestedPlan(Model model, @PathVariable int reqPlanId) {
        // Data displayed in table
        List<Hour> allHours = hourService.getHours();
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", reqPlanId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", reqPlanId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", reqPlanId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", reqPlanId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", reqPlanId);
        List<Lesson> saturdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Saturday", reqPlanId);
        List<Lesson> sundayLessons = lessonService.getLessonsForSpecificDayAndPlan("Sunday", reqPlanId);

        // Toolbar list
        List<Plan> allPlans = planService.getAllPlans();

        /* Name used to display on page
         * Usage:
         *  - renaming plan
         *  - lesson editing */
        Plan currentPlan = planService.getPlanById(reqPlanId);

        // Used to fill lesson editor form
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        // Get logged user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        // Collect only admin role
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for (Role role : loggedUserRoles) {
            if (role.getRole().equals("ADMIN")) log_user_role = String.valueOf(role.getRole());
        }

        model.addAttribute("hourData", allHours);
        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);
        model.addAttribute("saturdayData", saturdayLessons);
        model.addAttribute("sundayData", sundayLessons);
        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);
        model.addAttribute("log_user_mail", loggedUser.getName());
        model.addAttribute("log_user_role", log_user_role);

        return "plan";
    }

    @RequestMapping("/plan-read-only-{reqPlanId}")
    public String getRequestedPlanReadOnly(Model model, @PathVariable int reqPlanId) {
        // Data displayed in table
        List<Hour> allHours = hourService.getHours();
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", reqPlanId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", reqPlanId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", reqPlanId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", reqPlanId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", reqPlanId);
        List<Lesson> saturdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Saturday", reqPlanId);
        List<Lesson> sundayLessons = lessonService.getLessonsForSpecificDayAndPlan("Sunday", reqPlanId);

        // Toolbar list
        List<Plan> allPlans = planService.getAllPlans();

        /* Name used to display on page
         * Usage:
         *  - renaming plan
         *  - lesson editing */
        Plan currentPlan = planService.getPlanById(reqPlanId);

        // Used to fill lesson editor form
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        // Get logged user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        // Collect only admin role
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for (Role role : loggedUserRoles) {
            if (role.getRole().equals("ADMIN")) log_user_role = String.valueOf(role.getRole());
        }

        model.addAttribute("hourData", allHours);
        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);
        model.addAttribute("saturdayData", saturdayLessons);
        model.addAttribute("sundayData", sundayLessons);
        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);
        model.addAttribute("log_user_mail", loggedUser.getName());
        model.addAttribute("log_user_role", log_user_role);

        return "plan-read-only";
    }

    @RequestMapping("/rename-plan")
    public String renamePlan(@RequestParam("planId") int id, @RequestParam("planName") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);
        planService.savePlan(existingPlan);

        return "redirect:/plan-" + id;
    }

    @Transactional
    @RequestMapping("/delete-plan-{planId}")
    public String deletePlan(@PathVariable("planId") int id) {
        // Delete all lessons from current plan
        lessonService.deleteLessonByPlanId(id);
        // Delete plan
        planService.deletePlanById(id);

        return "redirect:/plans";
    }

    // Generate new table
    @RequestMapping("/generate-new-table-{plan_id}")
    public String generateNewTable(@PathVariable int plan_id) {
        int dayId = 1;
        int hourId = 1;

        for (int j = 0; j < 7; j++) {

            switch (j) {
                case 0: {
                    dayId = 1;
                    break;
                }
                case 1: {
                    dayId = 11;
                    break;
                }
                case 2: {
                    dayId = 21;
                    break;
                }
                case 3: {
                    dayId = 31;
                    break;
                }
                case 4: {
                    dayId = 41;
                    break;
                }
                case 5: {
                    dayId = 51;
                    break;
                }
                case 6: {
                    dayId = 61;
                    break;
                }
            }

            for (int i = 1; i <= 8; i++) {
                switch (i) {
                    case 1: {
                        hourId = 1;
                        break;
                    }
                    case 2: {
                        hourId = 11;
                        break;
                    }
                    case 3: {
                        hourId = 21;
                        break;
                    }
                    case 4: {
                        hourId = 31;
                        break;
                    }
                    case 5: {
                        hourId = 41;
                        break;
                    }
                    case 6: {
                        hourId = 51;
                        break;
                    }
                    case 7: {
                        hourId = 61;
                        break;
                    }
                }

                Lesson newLesson = new Lesson();
                newLesson.setHour(hourRepository.getOne(hourId));
                newLesson.setRoom(roomRepository.getOne(1));
                newLesson.setTeacher(teacherRepository.getOne(1));
                newLesson.setGroup(groupRepository.getOne(1));
                newLesson.setDay(dayRepository.getOne(dayId));
                newLesson.setPlan(planRepository.getOne(plan_id));
                newLesson.setSubject(subjectRepository.getOne(1));
                lessonService.saveLesson(newLesson);
            }
        }

        return "redirect:/plan-" + plan_id;

    }

    // Generate new plan
    @RequestMapping("/generate-new-plan")
    public String generateNewPlan() {
        String defaultName = "New auto-generated plan";

        Plan defaultNewPlan = new Plan();
        defaultNewPlan.setName(defaultName);

        Plan savedPlan = planService.savePlan(defaultNewPlan);
        int savedPlanId = savedPlan.getId();

        return "redirect:/generate-new-table-" + savedPlanId;
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

    }
}
