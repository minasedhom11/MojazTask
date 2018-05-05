package com.av.mojaztask.networkUtilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {

        private CheckNetwork() {
            // This utility class is not publicly instantiable
        }
        public static boolean isNetworkConnected(Context context) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
}
