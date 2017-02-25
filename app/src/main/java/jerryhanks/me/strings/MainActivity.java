package jerryhanks.me.strings;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import jerryhanks.me.strings.view.BibleTextView;
import jerryhanks.me.strings.view.MyTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTextView mTextView = (MyTextView) findViewById(R.id.string_tv);

        BibleTextView mBibleTv = (BibleTextView) findViewById(R.id.bible_tv);

        StringBuilder bibleString = new StringBuilder("This is the bible TextView: Open John 3:16")
                .append(" and the John 3:16-18")
                .append(" and then 1 Corinthians 3:4")
                .append(" and the 2 Corinthians 4:6-8 and then we are ready to go with string manipulation.");

        StringBuilder sampleString = new StringBuilder();
        sampleString.append("Bold Span\n"); // 0-10
        sampleString.append("Foreground Span\n"); // 11-28
        sampleString.append("Background Span\n"); // 28-45
        sampleString.append("Clickable Span\n"); // 45 - 61
        sampleString.append("Url Span: Google.com\n"); // 61-83
        sampleString.append("S Subscript\n");
        sampleString.append("S SuperScript\n");
        sampleString.append("Underline Me\n");
        sampleString.append("Relative TextSize span\n");
        sampleString.append("Italicize me\n");
        sampleString.append("Strike Me Though\n");

        //build the spannable String
        SpannableString spannableString = new SpannableString(sampleString.toString());
        //add bold span
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, 10, 0);

        //add fore ground span
        spannableString.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)),
                10, 26, 0);

        //adding background Span
        spannableString.setSpan(new BackgroundColorSpan(
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)), 26, 42, 0);

        //adding click span
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //what happens whe i click
                Toast.makeText(getApplicationContext(), "You just clicked on a Click Span!", Toast.LENGTH_LONG).show();

            }
        };
        spannableString.setSpan(clickableSpan, 42, 56, 0);

        //add Url Span
        URLSpan urlSpan = new URLSpan("https://www.google.com") {
            @Override
            public void onClick(View widget) {
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(getURL()));
                startActivity(urlIntent);
            }
        };

        spannableString.setSpan(urlSpan, 55, 78, 0);

        //set the movement method for the TextView
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());

        //add subscript Span
        spannableString.setSpan(new SubscriptSpan(), 79, 89, 0);

        //add Superscript span
        spannableString.setSpan(new SuperscriptSpan(), 92, 104, 0);

        //add underline span
        spannableString.setSpan(new UnderlineSpan(), 104, 116, 0);

        //add relative size span
        spannableString.setSpan(new RelativeSizeSpan(1.5f), 116, 139, 0);

        //add italics span
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC), 139, 152, 0);

        //add strike through span
        spannableString.setSpan(new StrikethroughSpan(), 153, 170, 0);


        mTextView.setText(spannableString, TextView.BufferType.SPANNABLE);

        mBibleTv.setText(bibleString);

    }
}
