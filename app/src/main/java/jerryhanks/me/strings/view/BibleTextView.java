package jerryhanks.me.strings.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by @Po10cio on 25/02/2017 for Strings.
 */

public class BibleTextView extends TextView {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BibleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    public BibleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BibleTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        //define the matchin pattern
        Pattern pattern = Pattern
                .compile("((\\d)?\\s?(John|Corinthians)\\s+(\\d*)(:)?\\s*(\\d*)(\\s*(-|–)\\s*(\\d*))?)"
                        , Pattern.CASE_INSENSITIVE);
        //build a spannable String using the Consequence
        SpannableString spString = new SpannableString(text);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "You jus clicked on a Bible link", Toast.LENGTH_LONG).show();
                }
            };
            spString.setSpan(clickSpan, start, end, 0);
        }

        super.setText(spString, BufferType.SPANNABLE);
    }
}
