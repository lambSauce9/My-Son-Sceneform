/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.sceneform.samples.hellosceneform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class HelloSceneformActivity4 extends AppCompatActivity {
  private static final String TAG = HelloSceneformActivity4.class.getSimpleName();
  private static final double MIN_OPENGL_VERSION = 3.0;

  private ArFragment arFragment;
  private ModelRenderable modelRenderable;
  private ViewRenderable textViewRenderable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!checkIsSupportedDeviceOrFinish(this)) {
      return;
    }

    setContentView(R.layout.activity_ux);
    arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


    ModelRenderable.builder()
        .setSource(this, R.raw.laocoon)
        .build()
        .thenAccept(renderable -> modelRenderable = renderable)
        .exceptionally(
            throwable -> {
              Toast toast =
                  Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
              toast.setGravity(Gravity.CENTER, 0, 0);
              toast.show();
              return null;
            });
    ViewRenderable.builder()
            .setView(this,R.layout.textview4)
            .build()
            .thenAccept(renderable -> textViewRenderable = renderable);

      arFragment.setOnTapArPlaneListener(
              (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                  if (modelRenderable == null) {
                      return;
                  }

                  // Create the Anchor.
                  Anchor anchor = hitResult.createAnchor();
                  AnchorNode anchorNode = new AnchorNode(anchor);
                  anchorNode.setParent(arFragment.getArSceneView().getScene());

                  // Create the transformable andy and add it to the anchor.
                  TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                  andy.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 180f));
                  andy.setParent(anchorNode);
                  andy.setRenderable(modelRenderable);
                  andy.select();

                  andy.setOnTapListener(new Node.OnTapListener() {
                      @Override
                      public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                          TransformableNode text = new TransformableNode(arFragment.getTransformationSystem());
                          text.setParent(andy);
                          text.setLocalPosition(new Vector3(-0.85f, 0.65f, 0.65f));
                          text.setRenderable(textViewRenderable);
                          text.select();
                          andy.setOnTapListener(null);
                      }
                  });

//
//                  Button textButton = findViewById(R.id.TextButton);
//                  textButton.setVisibility(View.VISIBLE);
//                  textButton.setOnClickListener(view -> {
//                      textButton.setVisibility(View.GONE);
//                      TransformableNode text = new TransformableNode(arFragment.getTransformationSystem());
//                      text.setParent(andy);
//                      text.setLocalPosition(new Vector3(0.65f, 0.8f, 0.65f));
//                      text.setRenderable(textViewRenderable);
//                      text.select();
//                  });

              });
  }


  public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
    if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
      Log.e(TAG, "Sceneform requires Android N or later");
      Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
      activity.finish();
      return false;
    }
    String openGlVersionString =
        ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
            .getDeviceConfigurationInfo()
            .getGlEsVersion();
    if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
      Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
      Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
          .show();
      activity.finish();
      return false;
    }
    return true;
  }
}
