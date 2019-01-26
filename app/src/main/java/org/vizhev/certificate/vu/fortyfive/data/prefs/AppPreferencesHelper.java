package org.vizhev.certificate.vu.fortyfive.data.prefs;

import android.content.Context;
import com.ironz.binaryprefs.BinaryPreferencesBuilder;
import com.ironz.binaryprefs.Preferences;
import org.vizhev.certificate.vu.fortyfive.dataclasses.GeneralContent;

public class AppPreferencesHelper implements PreferencesHelper {

    private Preferences preferences;

    public AppPreferencesHelper(Context context) {
        preferences = new BinaryPreferencesBuilder(context)
                .registerPersistable(GeneralContent.KEY, GeneralContent.class)
                .build();
    }


}
