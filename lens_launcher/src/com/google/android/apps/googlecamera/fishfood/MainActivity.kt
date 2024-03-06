/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.apps.googlecamera.fishfood

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : Activity() {
    companion object {
        private const val TAG = "LensLauncher"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            startActivityForResult(
                Intent()
                    .setAction(Intent.ACTION_VIEW)
                    .setData(Uri.parse("googleapp://lens"))
                    .setPackage("com.google.android.googlequicksearchbox"),
                0
            )
        } catch (e: ActivityNotFoundException) {
            handleLensNotFound(e)
        }
        finish()
    }

    private fun handleLensNotFound(e: ActivityNotFoundException) {
        Log.e(TAG, "Unable to start Lens intent", e)
        showToast(R.string.lens_not_found, Toast.LENGTH_LONG)
        finish()
    }

    private fun showToast(resId: Int, duration: Int) {
        Toast.makeText(applicationContext, resId, duration).show()
    }
}
