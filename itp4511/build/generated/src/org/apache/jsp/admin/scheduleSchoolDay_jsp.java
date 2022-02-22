package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import system.bean.LectureBean;
import system.bean.LectureDayBean;
import system.bean.LectureTimeBean;
import system.bean.SchedulerBean;

public final class scheduleSchoolDay_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/tlds/nav-taglib.tld");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    String[] dayOfweeks = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] schoolDay = {"A", "B", "C", "D", "E", "F"};
    GregorianCalendar calendar = new GregorianCalendar(2019, 8, 1);
    GregorianCalendar preCalendar = new GregorianCalendar(2019, 7, 1);
    SimpleDateFormat dateFormal = new SimpleDateFormat("yyyy-MM-dd");
    String event = "";

      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <title>Admin Area | Dashboard</title>\n");
      out.write("        <!-- Bootstrap core CSS -->\n");
      out.write("        <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\n");
      out.write("              rel=\"stylesheet\">\n");
      out.write("        <link href=\"../css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"../css/style.css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../header.jsp", out, false);
      out.write("\n");
      out.write("            <section id=\"main\">\n");
      out.write("                <div class=\"container\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-3\">\n");
      out.write("                        \n");
      out.write("                        ");
      if (_jspx_meth_nav_showNav_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"col-lg-9\">\n");
      out.write("                        <!-- Latest Users -->\n");
      out.write("                        <div class=\"card\">\n");
      out.write("                            <div class=\"card-header main-color-bg\">\n");
      out.write("                                <h5 class=\"card-title\">Scheel Calendar List</h5>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <div class=\"card-body\">\n");
      out.write("                                ");

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
                                
      out.write("\n");
      out.write("                                <br>\n");
      out.write("                                <form action=\"schedule?class=schoolDay\" method=\"POST\" class=\"format\">\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-primary\">Add Day</button>\n");
      out.write("                                    <input type=\"hidden\" name=\"action\" value=\"addPage\">\n");
      out.write("                                </form>\n");
      out.write("                                <form class=\"form-inline float-right\" action=\"schedule?class=schoolDay\" method=\"POST\">\n");
      out.write("                                    <div class=\"form-group mb-2\">\n");
      out.write("                                        <font>Search: </font>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group mx-sm-3 mb-2\">\n");
      out.write("                                        <input list=\"searchlist\" type=\"text\" class=\"form-control\" name=\"searchVal\" id=\"searchVal\" placeholder=\"Search...\">\n");
      out.write("                                    </div>\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-dark mb-2\">Search</button>\n");
      out.write("                                    <input type=\"hidden\" name=\"action\" value=\"search\">\n");
      out.write("                                </form>\n");
      out.write("                                ");
      java.util.ArrayList<system.bean.SchedulerBean> scheduleAllList = null;
      synchronized (request) {
        scheduleAllList = (java.util.ArrayList<system.bean.SchedulerBean>) _jspx_page_context.getAttribute("scheduleAllList", PageContext.REQUEST_SCOPE);
        if (scheduleAllList == null){
          try {
            scheduleAllList = (java.util.ArrayList<system.bean.SchedulerBean>) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "java.util.ArrayList<system.bean.SchedulerBean>");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "java.util.ArrayList<system.bean.SchedulerBean>", exc);
          }
          _jspx_page_context.setAttribute("scheduleAllList", scheduleAllList, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\n");
      out.write("                                <table class=\"table\">\n");
      out.write("                                    <thead class=\"thead-dark\">\n");
      out.write("                                    <th>Title</th>\n");
      out.write("                                    <th>Start Date</th>\n");
      out.write("                                    <th>End Date</th>\n");
      out.write("                                    <th>Holiday</th>\n");
      out.write("                                    <th>School Day</th>\n");
      out.write("                                    <th>Action</th>\n");
      out.write("                                     </thead>\n");
      out.write("                                        ");

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
                                        
      out.write("\n");
      out.write("                                </table>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <a href=\"schedule\"><button type=\"button\" class=\"btn btn-secondary\">Back</button></a>\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"card\">\n");
      out.write("                            <div class=\"card-header main-color-bg\">\n");
      out.write("                                <h5 class=\"card-title\">Scheel Calendar</h5>\n");
      out.write("                            </div>\n");
      out.write("                            ");
      java.util.ArrayList<system.bean.SchedulerBean> scheduleList = null;
      synchronized (request) {
        scheduleList = (java.util.ArrayList<system.bean.SchedulerBean>) _jspx_page_context.getAttribute("scheduleList", PageContext.REQUEST_SCOPE);
        if (scheduleList == null){
          try {
            scheduleList = (java.util.ArrayList<system.bean.SchedulerBean>) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "java.util.ArrayList<system.bean.SchedulerBean>");
          } catch (ClassNotFoundException exc) {
            throw new InstantiationException(exc.getMessage());
          } catch (Exception exc) {
            throw new ServletException("Cannot create bean of class " + "java.util.ArrayList<system.bean.SchedulerBean>", exc);
          }
          _jspx_page_context.setAttribute("scheduleList", scheduleList, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\n");
      out.write("                            <div class=\"card-body\">\n");
      out.write("                                <table class=\"table\">\n");
      out.write("                                    <thead>\n");
      out.write("                                        ");
                                            out.print("<tr>");
                                            out.print("<th>Month</th>");
                                            for (String dayOfweek : dayOfweeks) {
                                                out.print("<th>" + dayOfweek + "</th>");
                                            }
                                            out.print("<th>Events</th>");
                                            out.print("</tr>");
                                        
      out.write("\n");
      out.write("                                    </thead>\n");
      out.write("                                    ");

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
                                    
      out.write("\n");
      out.write("                                </table>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <a href=\"schedule\"><button type=\"button\" class=\"btn btn-secondary\">Back</button></a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <br><br>\n");
      out.write("        <footer></footer>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- Placed at the end of the document so the pages load faster -->\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\"></script>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_nav_showNav_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  nav:showNav
    system.tag.NavTag _jspx_th_nav_showNav_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(system.tag.NavTag.class) : new system.tag.NavTag();
    _jspx_th_nav_showNav_0.setJspContext(_jspx_page_context);
    _jspx_th_nav_showNav_0.setRole("Admin");
    _jspx_th_nav_showNav_0.setActive("Schdeule");
    _jspx_th_nav_showNav_0.doTag();
    return false;
  }
}
