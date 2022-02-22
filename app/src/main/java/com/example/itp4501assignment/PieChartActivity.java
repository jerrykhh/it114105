package com.example.itp4501assignment;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Toast;

        import java.text.DecimalFormat;


public class PieChartActivity extends AppCompatActivity {
    SQLiteDatabase db;
    int countTRUE;
    int countFALSE;
    float countTruePre = 0, countFalsePre = 0;
    DecimalFormat decimalFormat = new DecimalFormat("0.00"); // set the val format with 0.00
    Cursor cursor = null;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));

        try {
            // Create a database if it does not exist
            db = SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.OPEN_READONLY); // open the Database with the path

            cursor = db.rawQuery("SELECT COUNT(*) FROM GamesLog WHERE winOrLost=0",null); // COUNT the Lose SQL

            if (cursor.moveToNext()) //check the sql whether have the result
                countFALSE = cursor.getInt(0); // put the result to variable
            else
                countFALSE = 0; // if not result put the zero to variable
            cursor = db.rawQuery("SELECT COUNT(*) FROM GamesLog WHERE winOrLost=1",null); // COUNT the WIN SQL
            if (cursor.moveToNext()) // check the sql whether have the result
                countTRUE = cursor.getInt(0); //put the result to variable
            else
                countTRUE = 0;// if not result put the zero to variable

            if(countTRUE==0)
                countTruePre=0;
            else
                countTruePre = countTRUE / (float) (countTRUE + countFALSE); // calculate the Win percentage
            if(countFALSE==0)
                countFalsePre=0;
            else
                countFalsePre = countFALSE / (float) (countTRUE + countFALSE); // calculate the Lose percentage

        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); // show the error message
            // if the exception occured set the default value
            countFALSE = 0;
            countTRUE = 0;
            countTruePre = (float) 0;
            countFalsePre = (float) 0;
        }

    }

    class Panel extends View {
        public Panel(Context context) {

            super(context); // set the panel to draw

        }

        public void onDraw(Canvas c) {
            super.onDraw(c);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL); //set the paint is Fill
            paint.setColor(Color.WHITE); // set the background is white
            paint.setAntiAlias(true); // set the draw the shape is antiAlias
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(2);
            //drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
            c.drawLine(150, 150, 150, 850, paint);// draw the bar chart Y line
            c.drawLine(150, 850, 980, 850, paint); // draw the bar chary X line
            //Rect TRUE //(150-848)/100*countTruePre
            paint.setColor(Color.GREEN); // set the draw color is green
            c.drawRect(284, (848 - 150) - ((848 - 150) * countTruePre) + 150, 484, 848, paint);
            // draw the win Rectangle
            c.drawCircle(300, 1090, 90, paint);
            // draw the win Circle
            paint.setColor(Color.RED); // set the draw color is red
            c.drawRect(634, (848 - 150) - ((848 - 150) * countFalsePre) + 150, 834, 848, paint);
            // draw the Lose Rectangle
            c.drawCircle(300, 1330, 90, paint);
            // draw the Lose Circle
            // %
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(30); //set the draw text text size is 30
            paint.setTypeface(Typeface.SERIF);// font family is SERID
            c.drawText(decimalFormat.format(countTruePre * 100 )+ "%", 336, (848 - 150) - ((848 - 150) * countTruePre) + 120, paint);
            //draw the bar chart of WIN %
            c.drawText(decimalFormat.format(countFalsePre * 100) + "%", 679, (848 - 150) - ((848 - 150) * countFalsePre) + 120, paint);
            //draw the bar chart of Lose %
            paint.setTextSize(35);//set the draw text text size is 35
            c.drawText("Count: " + countTRUE + ",", 420, 1088, paint); //draw the count vale in circle right side
            c.drawText("Percentage: " + decimalFormat.format(countTruePre * 100) + "%", 420, 1135, paint);
            // draw the win Percentage val in the circle right side
            c.drawText("Count: " + countFALSE + ",", 420, 1338, paint);
            //draw the lose Percentage val in the circle right side
            c.drawText("Percentage: " + decimalFormat.format(countFalsePre * 100) + "%", 420, 1385, paint);
            // draw the lose Percentage val in the circle right side
            //set the font family
            paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
            c.drawText("WIN", 350, 900, paint); // draw the Win in bar chart X title
            c.drawText("LOSE", 693, 900, paint); // draw the LOSE in bar chart Y title
            paint.setTextSize(50); //set Text Size is 50
            c.drawText("WIN", 254, 1110, paint); // draw the WIN in Circle
            c.drawText("LOSE", 243, 1352, paint); // draw the LOSE in Circle


            c.drawText("GameDate", 75, 1575, paint); // draw the GameDate column title
            c.drawText("OpponentName", 355, 1575, paint); // draw the OpponentName title
            c.drawText("winOrLost", 760, 1575, paint); // draw the winOrLost column title
            paint.setStyle(Paint.Style.FILL);
            paint.setTypeface(Typeface.DEFAULT); // set the default font family
            int locationY=1575+70; // set the row margin-bottom
            String winOrLost;
            try {
                cursor = null;
                cursor = db.rawQuery("SELECT * FROM GamesLog ORDER BY gameDate DESC limit 3", null);
                //GET the least 3 Game LOG information SQL
                while (cursor.moveToNext()) {
                    String gameDate = cursor.getString(cursor.getColumnIndex("gameDate"));
                    //get the Column gameDate val and store to gameDate variable
                    String opponentName = cursor.getString(cursor.getColumnIndex("opponentName"));
                    //get the Column opponentName val and store to opponentName variable

                    int checkval = cursor.getInt(cursor.getColumnIndex("winOrLost"));
                    //get the winOrLost val and store to checkval variable

                    c.drawText(gameDate, 70, locationY, paint); // draw the gameDate
                    c.drawText(opponentName, 465, locationY, paint); // draw the opponent Name
                    if(checkval == 0) // check the winORLost Val
                        winOrLost = "LOST"; // if 0 equal to LOST
                    else
                        winOrLost = "WINS"; // NOT 0 is Win
                    c.drawText(winOrLost, 805, locationY, paint); //draw the win OR Lost
                    locationY+=70; // set the margin-bottom

                }
            } catch (SQLiteException e) {

            }
            paint.setTextSize(30);
            c.drawText("Last 3 Game History", 70, locationY-20, paint); //draw the hits of last 4 game history

        }

    }
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // set the animation of quit the class
    }
}







