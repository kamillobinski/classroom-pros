package com.example.demo.service;

import com.example.demo.repository.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PlanService planService;

    @Autowired
    private HourService hourService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Lesson saveLesson (Lesson lesson)
    {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> saveLessons(List<Lesson> lessons){
        return lessonRepository.saveAll(lessons);
    }

    public Lesson getLessonById(int id){
        return lessonRepository.findById(id).orElse(null);
    }

    public List<Lesson> getLessons(){
        return lessonRepository.findAll();
    }

    public String deleteLesson(int id){
        lessonRepository.deleteById(id);
        return "Lesson was successfully removed!";
    }

    public Model requestLessonUpdate(Model model, int lessonId, int planId) {
        Lesson currentLesson = lessonRepository.findById(lessonId).orElse(null);

        List<Lesson> mondayLessons = lessonService.getLessonsForSpecificDayAndPlan("Monday", planId);
        List<Lesson> tuesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Tuesday", planId);
        List<Lesson> wednesdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Wednesday", planId);
        List<Lesson> thursdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Thursday", planId);
        List<Lesson> fridayLessons = lessonService.getLessonsForSpecificDayAndPlan("Friday", planId);
        List<Lesson> saturdayLessons = lessonService.getLessonsForSpecificDayAndPlan("Saturday", planId);
        List<Lesson> sundayLessons = lessonService.getLessonsForSpecificDayAndPlan("Sunday", planId);

        List<Plan> allPlans = planService.getAllPlans();
        Plan currentPlan = planRepository.findById(planId);
        List<Hour> allHours = hourService.getHours();

        List<Subject> allSubjects = subjectRepository.findAll();
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();

        // Data of the selected lesson,
        // will be inserted into the lesson editor
        int selectedLessonSubject = currentLesson.getSubject().getSubject_id();
        int selectedLessonTeacher = currentLesson.getTeacher().getTeacher_id();
        int selectedLessonRoom = currentLesson.getRoom().getRoom_id();

        // To display time and day of the currently edited lesson
        Day currentDay = currentLesson.getDay();
        Hour currentHour = currentLesson.getHour();

        // Get logged user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getUserByEmail(auth.getName());

        model.addAttribute("selectedLessonSubject", selectedLessonSubject);
        model.addAttribute("selectedLessonTeacher", selectedLessonTeacher);
        model.addAttribute("selectedLessonRoom", selectedLessonRoom);

        model.addAttribute("mondayData", mondayLessons);
        model.addAttribute("tuesdayData", tuesdayLessons);
        model.addAttribute("wednesdayData", wednesdayLessons);
        model.addAttribute("thursdayData", thursdayLessons);
        model.addAttribute("fridayData", fridayLessons);
        model.addAttribute("saturdayData", saturdayLessons);
        model.addAttribute("sundayData", sundayLessons);

        model.addAttribute("allPlans", allPlans);
        model.addAttribute("currentPlan", currentPlan);
        model.addAttribute("hourData", allHours);

        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allRooms", allRooms);

        model.addAttribute("editedLessonId", lessonId);
        model.addAttribute("currentPlan", currentPlan);

        model.addAttribute("currentDay", currentDay);
        model.addAttribute("currentHour", currentHour);

        model.addAttribute("log_user_mail", loggedUser.getName());

        return model;
    }

    @Transactional
    public void updateLesson(int lessonId, int planId, int subjectId, int teacherId, int roomId){
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        lesson.setRoom(room);

        lessonRepository.save(lesson);
    }

    public List<Lesson> getLessonsForSpecificDayOfTheWeek(String day){
        return lessonRepository.findByDayNameOrderByHourAsc(day);
    }

    public List<Lesson> getLessonsForSpecificDayAndPlan(String day, int id){
        return lessonRepository.findByDayNameAndPlanIdOrderByHourAsc(day, id);
    }

    public void deleteLessonByPlanId(int id) { lessonRepository.deleteLessonByPlanId(id); }

}
