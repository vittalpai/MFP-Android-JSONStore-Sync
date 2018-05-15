package com.ibm.mobile.jsonstoreofflinesync.background;/*
 *     Copyright 2018 IBM Corp.
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */



import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ibm.mobile.jsonstoreofflinesync.manager.FlightAttendantManager;
import com.ibm.mobile.jsonstoreofflinesync.manager.FoodMenuSyncListener;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResourceRequest;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;

import java.net.URI;


/**
 * Created by norton on 12/28/17.
 */

public class JSONStoreBackgroundService extends IntentService {


    public JSONStoreBackgroundService() {
        super("JSONStoreBackgroundService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("AppLaunchBackgroundSer","handleintenet");
        FlightAttendantManager.getInstance().upstreamsync();
        FlightAttendantManager.getInstance().syncFoodMenu(new FoodMenuSyncListener() {
            @Override
            public void foodSyncComplete(String message) {
                Log.i("AppLaunchBackgroundSer","Successfully loaded food menu from cloudant ");
            }
        });
    }
}
