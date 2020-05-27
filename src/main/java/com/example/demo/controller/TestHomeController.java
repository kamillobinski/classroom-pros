package com.example.demo.controller;

import com.example.demo.repository.*;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class TestHomeController {

    @Autowired
    private HourService hourService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PlanService planService;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/test-request-lesson-update-{lessonId}-{planId}")
    public String requestLessonUpdate(Model model, @PathVariable int lessonId, @PathVariable int planId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", planId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", planId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", planId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", planId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", planId);

        List<Plan> allPlans = planService.getAllPlans();
        Plan currentPlan = planService.getPlanById(planId);
        List<Hour> allHours = hourService.getHours();

        // Used in the lesson edit form
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        model.addAttribute("selectedLessonSubject", lesson.getSubject().getSubject_id());
        model.addAttribute("selectedLessonTeacher", lesson.getTeacher().getTeacher_id());
        model.addAttribute("selectedLessonRoom", lesson.getRoom().getRoom_id());

        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("hourData", allHours);

        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);

        model.addAttribute("editedLessonId", lessonId);
        model.addAttribute("editedPlanId", planId);

        return "homepage-test";
    }

    @RequestMapping("/test-lesson-update")
    public String updateLesson(Model model, @RequestParam int lessonId, @RequestParam int planId, @RequestParam int subjectId, @RequestParam int teacherId, @RequestParam int roomId) {

        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        lesson.setRoom(room);

        lessonRepository.save(lesson);

        return "redirect:/test-home-" + planId;
    }

    // Generate new table
    @RequestMapping("/test-generate-new-table-{plan_id}")
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

    // Generate new plan
    @RequestMapping("/test-generate-new-plan")
    public String generateNewPlan(ModelMap model) {
        String defaultName = "New auto-generated plan";

        Plan defaultNewPlan = new Plan();
        defaultNewPlan.setName(defaultName);

        Plan savedPlan = planService.savePlan(defaultNewPlan);
        int savedPlanId = savedPlan.getId();

        return "redirect:/test-generate-new-table-" + savedPlanId;
    }

    // Homepage - TEST VERSION
    // With actual plan
    @RequestMapping("/test-home-{requestedPlanId}")
    public String getTestHomepage(Model model, @PathVariable int requestedPlanId) {
        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", requestedPlanId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", requestedPlanId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", requestedPlanId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", requestedPlanId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", requestedPlanId);

        List<Plan> allPlans = planService.getAllPlans();
        Plan currentPlan = planService.getPlanById(requestedPlanId);
        List<Hour> allHours = hourService.getHours();

        // Used in the lesson edit form
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("hourData", allHours);

        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);

        return "homepage-test";
    }

    // Homepage - TEST VERSION
    // Without data
    @RequestMapping("/test-home")
    public String getEmptyTestHomepage(Model model) {
        List<Plan> allPlans = planService.getAllPlans();
        model.addAttribute("allPlans", allPlans);

        return "homepage-test-empty";
    }

    // Change plan name
    @RequestMapping("/test-change-current-plan-name")
    public String changePlanName(Model model, @RequestParam("current-plan-id") int id, @RequestParam("changed-plan-name") String name) {
        Plan existingPlan = planService.getPlanById(id);
        existingPlan.setName(name);

        planService.savePlan(existingPlan);

        return "redirect:/test-home-" + id;
    }

    @Transactional
    @RequestMapping("/test-delete-current-plan-{current-plan-id}")
    public String deleteCurrentPlan(Model model, @PathVariable("current-plan-id") int id) {
        lessonService.deleteLessonByPlanId(id);
        planService.deletePlanById(id);

        return "redirect:/test-home";
    }
}
