<%-- 
    Document   : dashboard
    Created on : 2019/11/11, 下午 10:08:39
    Author     : JerryKwok
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="system.bean.LectureBean, system.bean.LectureDayBean, system.bean.LectureTimeBean, system.bean.SchedulerBean"%>
<!DOCTYPE html>
<%
    String[] dayOfweeks = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] schoolDay = {"A", "B", "C", "D", "E", "F"};
    GregorianCalendar calendar = new GregorianCalendar(2019, 8, 1);
    GregorianCalendar preCalendar = new GregorianCalendar(2019, 7, 1);
    SimpleDateFormat dateFormal = new SimpleDateFormat("yyyy-MM-dd");
    String event = "";
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Area | Dashboard</title>
        <!-- Bootstrap core CSS -->
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
                        <nav:showNav role="Admin" active="Schdeule" />
                    </div>

                    <div class="col-lg-9">
                        <!-- Latest Users -->
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Scheel Calendar List</h5>
                            </div>

                            <div class="card-body">
                                <%
                                    if (request.getAttribute("deleteMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Schedule #" + request.getAttribute("deleteMes") + " Deleted </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                    if (request.getAttribute("addMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Schedule Added </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                    if (request.getAttribute("saveMes") != null) {
                                        out.print("<div class='alert alert-success alert-dismissible fade show formated-table' role='alert'>");
                                        out.print("<strong>Schedule #" + request.getAttribute("saveMes") + " Saved </strong>");
                                        out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
                                        out.print("<span aria-hidden='true'>&times;</span></button></div>");
                                    }
                                %>
                                <br>
                                <form action="schedule?class=schoolDay" method="POST" class="format">
                                    <button type="submit" class="btn btn-primary">Add Day</button>
                                    <input type="hidden" name="action" value="addPage">
                                </form>
                                <form class="form-inline float-right" action="schedule?class=schoolDay" method="POST">
                                    <div class="form-group mb-2">
                                        <font>Search: </font>
                                    </div>
                                    <div class="form-group mx-sm-3 mb-2">
                                        <input list="searchlist" type="text" class="form-control" name="searchVal" id="searchVal" placeholder="Search...">
                                    </div>
                                    <button type="submit" class="btn btn-dark mb-2">Search</button>
                                    <input type="hidden" name="action" value="search">
                                </form>
                                <jsp:useBean id="scheduleAllList" scope="request" class="java.util.ArrayList<system.bean.SchedulerBean>"/>
                                <table class="table">
                                    <thead class="thead-dark">
                                    <th>Title</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Holiday</th>
                                    <th>School Day</th>
                                    <th>Action</th>
                                     </thead>
                                        <%
                                            for (SchedulerBean schedule : scheduleAllList) {
                                                out.print("<tr>");
                                                out.print("<td>" + schedule.getTitle() + "</td>");
                                                out.print("<td>" + schedule.getStart_date() + "</td>");
                                                out.print("<td>" + schedule.getEnd_date() + "</td>");
                                                out.print("<td>" + schedule.isHoliday() + "</td>");
                                                out.print("<td>" + schedule.isSchoolDay() + "</td>");
                                                out.print("<td>");
                                                out.print("<a href='schedule?class=schoolDay&id=" + schedule.getId() + "'><button type='button' class='btn btn-info tableBtn'><i class='material-icons'>edit</i></button></a> ");
                                                out.print("<a href='schedule?class=schoolDay&id=" + schedule.getId() + "&action=delete'><button type='button' class='btn btn-danger tableBtn'><i class='material-icons'>delete</i></button></a>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                            }
                                        %>
                                </table>
                            </div>
                        </div>
                        <a href="schedule"><button type="button" class="btn btn-secondary">Back</button></a>
                        <br>
                        <div class="card">
                            <div class="card-header main-color-bg">
                                <h5 class="card-title">Scheel Calendar</h5>
                            </div>
                            <jsp:useBean id="scheduleList" scope="request" class="java.util.ArrayList<system.bean.SchedulerBean>"/>
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                        <%                                            out.print("<tr>");
                                            out.print("<th>Month</th>");
                                            for (String dayOfweek : dayOfweeks) {
                                                out.print("<th>" + dayOfweek + "</th>");
                                            }
                                            out.print("<th>Events</th>");
                                            out.print("</tr>");
                                        %>
                                    </thead>
                                    <%
                                        out.print("<tr>");
                                        int schoolDayPointer = 0;
                                        int lastCache = 0;
                                        for (int i = 0; i <= months.length; i++) {
                                            int fillError = 0;
                                            if (calendar.get(Calendar.MONTH) == 4 || calendar.get(Calendar.MONTH) == 7) {
                                                fillError = 1;
                                            }
                                            out.print("<td rowspan=\"" + (Calendar.WEEK_OF_MONTH + 1 + fillError) + "\">" + (calendar.get(Calendar.MONTH) + 1) + "</td>");
                                            System.out.println((Calendar.WEEK_OF_MONTH + 1 + fillError) + "testW");
                                            int weekDay = 7;
                                            for (int countDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH); countDay > 0; countDay--) {
                                                boolean hasEventDay = false;
                                                if (schoolDayPointer == schoolDay.length) {
                                                    schoolDayPointer = 0;
                                                }
                                                if (weekDay <= 0) {
                                                    out.print("<td>" + event + "</td>");
                                                    out.print("</tr>");
                                                    out.print("<tr>");
                                                    weekDay = 7;
                                                    event = "";
                                                }

                                                String today = dateFormal.format(calendar.getTime());
                                                System.out.println(today);
                                                for (SchedulerBean schedule : scheduleList) {
                                                    if (schedule.getStart_date().equalsIgnoreCase(today)) {
                                                        hasEventDay = true;
                                                        if (!schedule.getEnd_date().equalsIgnoreCase(schedule.getStart_date())) {
                                                            event += schedule.getStart_day_Date() + "." + schedule.getStart_day_Month() + " - " + schedule.getEnd_day_Date() + "." + schedule.getEnd_day_Month() + " " + schedule.getTitle() + "<br>";
                                                            System.out.println(schedule.getCountDate());

                                                            for (int count = schedule.getCountDate(); count >= 0; count--) {
                                                                if (count == 0) {
                                                                    hasEventDay = false;
                                                                }
                                                                if (calendar.get(Calendar.DATE) == 1 && lastCache > 0) {
                                                                    if (lastCache >= 7) {
                                                                        out.print("<td>" + calendar.get(Calendar.DATE) + "*</td>");
                                                                    } else {
                                                                        for (int printCache = lastCache; printCache > 0; printCache--) {
                                                                            System.out.println("<td></td>");
                                                                        }
                                                                        out.print("<td>" + calendar.get(Calendar.DATE) + "*</td>");
                                                                        weekDay -= lastCache;
                                                                    }
                                                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                                                                    out.print("<td>" + calendar.get(Calendar.DATE) + "*</td>");
                                                                } else {
                                                                    if (weekDay == 0) {
                                                                        out.print("<td>" + event + "</td>");
                                                                        out.print("</tr>");
                                                                        out.print("<tr>");
                                                                        if (schedule.isHoliday() || !schedule.isSchoolDay()) {
                                                                            out.print("<td>*" + calendar.get(Calendar.DATE) + "*</td>");
                                                                        } else {
                                                                            out.print("<td>*" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "^</td>");
                                                                            schoolDayPointer++;
                                                                            if (schoolDayPointer == schoolDay.length) {
                                                                                schoolDayPointer = 0;
                                                                            }
                                                                        }
                                                                        weekDay = 7;
                                                                    } else {
                                                                        if (schedule.isHoliday() || !schedule.isSchoolDay()) {
                                                                            out.print("<td>" + calendar.get(Calendar.DATE) + "*</td>");
                                                                        } else {
                                                                            out.print("<td>" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "^</td>");
                                                                            schoolDayPointer++;
                                                                            if (schoolDayPointer == schoolDay.length) {
                                                                                schoolDayPointer = 0;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                calendar.add(Calendar.DAY_OF_WEEK, 1);
                                                                weekDay--;
                                                                countDay--;
                                                                System.out.println(weekDay + "zz");
                                                                System.out.println(calendar.get(Calendar.DATE) + "hlo");
                                                                System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
                                                                if (weekDay == 0) {
                                                                    out.print("<td>" + event + "</td>");
                                                                    out.print("</tr>");
                                                                    out.print("<tr>");
                                                                    weekDay = 7;
                                                                    event = "";
                                                                }
                                                            }
                                                        } else {
                                                            if (schedule.isHoliday() || !schedule.isSchoolDay()) {
                                                                out.print("<td>" + calendar.get(Calendar.DATE) + "*</td>");
                                                            } else {
                                                                out.print("<td>" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "^</td>");
                                                                schoolDayPointer++;
                                                                if (schoolDayPointer == schoolDay.length) {
                                                                    schoolDayPointer = 0;
                                                                }
                                                            }
                                                        }
                                                        event += schedule.getEnd_day_Date() + "." + schedule.getEnd_day_Month() + " " + schedule.getTitle() + "<br>";

                                                        System.out.println("TODAY " + today);
                                                    }

                                                }
                                                System.out.println(calendar.get(Calendar.DATE) + "weekday");
                                                System.out.println(calendar.get(Calendar.DAY_OF_WEEK) + "week");
                                                System.out.println(hasEventDay);

                                                if (!hasEventDay && calendar.get(Calendar.DATE) == 1 && lastCache >= 7) {
                                                    if (hasEventDay == false && (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7)) {
                                                        out.print("<td>" + calendar.get(Calendar.DATE) + "</td>");

                                                    } else if (!hasEventDay && calendar.get(Calendar.DAY_OF_WEEK) != 1 && calendar.get(Calendar.DAY_OF_WEEK) != 7) {
                                                        out.print("<td>" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "</td>");
                                                        schoolDayPointer++;
                                                    }
                                                } else if (!hasEventDay && calendar.get(Calendar.DATE) == 1 && lastCache > 0) {
                                                    for (int printCache = lastCache; printCache > 0; printCache--) {
                                                        out.print("<td></td>");
                                                    }
                                                    if (hasEventDay == false && (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7)) {
                                                        out.print("<td>" + calendar.get(Calendar.DATE) + "</td>");

                                                    } else if (!hasEventDay && calendar.get(Calendar.DAY_OF_WEEK) != 1 && calendar.get(Calendar.DAY_OF_WEEK) != 7) {
                                                        out.print("<td>" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "</td>");
                                                        schoolDayPointer++;
                                                    }
                                                    weekDay -= lastCache;
                                                } else if (!hasEventDay && calendar.get(Calendar.DAY_OF_WEEK) != 1 && calendar.get(Calendar.DAY_OF_WEEK) != 7) {
                                                    out.print("<td>" + calendar.get(Calendar.DATE) + schoolDay[schoolDayPointer] + "</td>");
                                                    schoolDayPointer++;
                                                } else if (hasEventDay == false && (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7)) {
                                                    out.print("<td>" + calendar.get(Calendar.DATE) + "</td>");
                                                }

                                                if (weekDay == 0) {
                                                    out.print("<td>" + event + "</td>");
                                                    out.print("</tr>");
                                                    out.print("<tr>");
                                                }
                                                System.out.println(weekDay);
                                                weekDay--;
                                                if (countDay == 1) {
                                                    lastCache = calendar.get(Calendar.DAY_OF_WEEK);
                                                }
                                                calendar.add(Calendar.DAY_OF_WEEK, 1);
                                            }
                                            out.print("</tr>");

                                        }
                                        out.print("</tr>");
                                    %>
                                </table>
                            </div>
                        </div>
                        <a href="schedule"><button type="button" class="btn btn-secondary">Back</button></a>
                    </div>
                </div>
            </div>
        </section>
        <br><br>
        <footer></footer>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
    </body>

</html>

