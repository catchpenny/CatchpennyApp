package cf.catchpenny.catchpennyapp.Preferences;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import cf.catchpenny.catchpennyapp.R;

public class LogoutDialogPreference extends DialogPreference {
    public LogoutDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Set the layout here
        setDialogLayoutResource(R.layout.logout_dialog_preference);

        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);


        setDialogIcon(null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            // User selected OK
        } else {
            // User selected Cancel
        }
    }

}