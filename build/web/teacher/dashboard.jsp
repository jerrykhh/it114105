<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.LectureBean, system.bean.LectureDayBean, system.bean.LectureTimeBean"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Teacher Area | Dashboard</title>
        <!-- Bootstrap core CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
              rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                        <%@taglib uri="/WEB-INF/tlds/nav-taglib.tld" prefix="nav" %>
                        <nav:showNav role="Teacher" active="dashboard" />
                        </div>

                        <div class="col-lg-9">
                            <!-- Website Overview -->
                            <div class="card">
                                <div class="card-header main-color-bg">
                                    <h5 class="card-title">Overview</h5>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-12">
                                            <div class="card bg-light card-body mb-3 dash-box">
                                                <h2><i class="material-icons">library_books</i> ${courseCount}</h2>
                                            <h4>Lecture</h4>
                                        </div>
                                    </div>
                                    <div class="col-md-6  col-sm-12">
                                        <div class="card bg-light card-body mb-3 dash-box">
                                            <h2> <i class="material-icons">school</i> ${studentCount}</h2>
                                            <h4>Student</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Latest Users -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Schedule</h5>
                            </div>
                            <div class="card-body">
                                <jsp:useBean id="dayList" scope="request" class="java.util.ArrayList<system.bean.LectureDayBean>"/>
                                <jsp:useBean id="timeList" scope="request" class="java.util.ArrayList<system.bean.LectureTimeBean>"/>
                                <jsp:useBean id="lectureList" scope="request" class="java.util.ArrayList<system.bean.LectureBean>"/>

                                <table class="table table-bordered table-hover text-center">

   <%
                                        out.print("<tr>");
                                        out.print("<th> Time </th>");
                                        for (LectureDayBean day : dayList) {
                                            out.print("<th>DAY " + day.getDay() + "</th>");
                                        }
                                        String[] schoolDays = {"A", "B", "C", "D", "E", "F"};
                                        out.print("</tr>");
                                        int countDay = dayList.size();
                                        for (LectureTimeBean time : timeList) {
                                            out.print("<tr>");
                                            out.print("<td>" + time.getStartTime() + "-" + time.getEndTime());
                                            int count = 0;
                                            int countPrint = 0;

                                           
                                            loopout:
                                            for (String schoolDay : schoolDays) { 
                                                boolean printed = false;
                                                System.out.println("TEST---" + schoolDay);
                                                for (LectureBean lecture : lectureList) {
                                                    if (lecture.getTime().getStartTime().equals(time.getStartTime()) && lecture.getTime().getEndTime().equals(time.getEndTime())) {
                                                        if (lecture.getTime().isFullweek()) {
                                                            System.out.println("FullWEEK");
                                                            out.print("<td colspan='" + dayList.size() + "'>" + lecture.getLecture() + "</td>");
                                                            break loopout;
                                                        } else {
                                                            System.out.println("Lecture---" + lecture.getDay().getDay());
                                                            if (schoolDay.equalsIgnoreCase(lecture.getDay().getDay())) {
                                                                System.out.println("printed");
                                                                out.print("<td>" + lecture.getLecture() + "</td>");
                                                                printed = true;
                                                                countPrint++;
                                                            }
                                                        }
                                                        
                                                    }
                                                    count++;

                                                }
                                                
                                                if (!printed) {
                                                    System.out.println("NOTPrintandPrintTDTD");
                                                    out.print("<td></td>");
                                                }
                                                if (lectureList.size() == 0) {
                                                    for (int i = dayList.size(); i > 0; i--) {
                                                        out.print("<td></td>");
                                                    }
                                                }
                                            }
                                            out.print("</tr>");
                                        }
                                    %>


                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>

