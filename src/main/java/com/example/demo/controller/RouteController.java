package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.HourService;
import com.example.demo.service.LessonService;
import com.example.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private HourService hourService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PlanService planService;

    // Sign in page
    @RequestMapping("/sign-in")
    public String getSignIn() {
        return "sign-in.html";
    }

    // Sign up
    @RequestMapping("/sign-up")
    public String getSignUp() {
        return "sign-up.html";
    }

    // Homepage
    @RequestMapping("/homepage")
    public String getHomepage() {
        return "homepage.html";
    }

    // Homepage - TEST VERSION
    // Without data
    @RequestMapping("/test-home")
    public String getEmptyTestHomepage(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);

        return "homepage-test-empty";
    }

    // Homepage - TEST VERSION
    // With actual plan
    @RequestMapping("/test-home-{requestedPlanId}")
    public String getTestHomepage(Model model, @PathVariable int requestedPlanId) {
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", requestedPlanId);
        model.addAttribute("mondayData", mondayLessons);

        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", requestedPlanId);
        model.addAttribute("tuesdayData", tuesdayLessons);

        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", requestedPlanId);
        model.addAttribute("wednesdayData", wednesdayLessons);

        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", requestedPlanId);
        model.addAttribute("thursdayData", thursdayLessons);

        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", requestedPlanId);
        model.addAttribute("fridayData", fridayLessons);

        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);

        Plan currentPlan = planService.getPlanById(requestedPlanId);
        model.addAttribute("currentPlan", currentPlan);

        List<Hour> allHours = hourService.getHours();
        model.addAttribute("hourData", allHours);

        // Used in the lesson edit form
        List<Subject> allSubjects = subjectRepository.findAll();
        model.addAttribute("allSubjects", allSubjects);

        List<Teacher> allTeachers = teacherRepository.findAll();
        model.addAttribute("allTeachers", allTeachers);

        List<Room> allRooms = roomRepository.findAll();
        model.addAttribute("allRooms", allRooms);

        return "homepage-test";
    }

    // Generate new plan
    @RequestMapping("/generate-new-plan")
    public String generateNewPlan(ModelMap model) {
        String defaultName = "New auto-generated plan";

        Plan defaultNewPlan = new Plan();
        defaultNewPlan.setName(defaultName);
        
        Plan savedPlan = planService.savePlan(defaultNewPlan);
        int savedPlanId = savedPlan.getId();

        return "redirect:/generate-new-table-" + savedPlanId;
    }

    // Generate new table
    @RequestMapping("/generate-new-table-{plan_id}")
    public String generateNewTable(Model model, @PathVariable int plan_id) {
        int dayId = 1;
        int hourId = 1;

        for(int j = 0; j < 5; j++) {

            switch(j) {
                case 0: { dayId = 1; break; }
                case 1: { dayId = 11; break; }
                case 2: { dayId = 21; break; }
                case 3: { dayId = 31; break; }
                case 4: { dayId = 41; break; }
            }

            for (int i = 1; i <= 8; i++) {
                switch(i) {
                    case 1: { hourId = 1; break; }
                    case 2: { hourId = 11; break; }
                    case 3: { hourId = 21; break; }
                    case 4: { hourId = 31; break; }
                    case 5: { hourId = 41; break; }
                    case 6: { hourId = 51; break; }
                    case 7: { hourId = 61; break; }
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

        return "redirect:/test-home-" + plan_id;
    }

}
