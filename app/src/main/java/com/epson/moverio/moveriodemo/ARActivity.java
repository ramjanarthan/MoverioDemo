package com.epson.moverio.moveriodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

import org.artoolkit.ar.jpct.ArJpctActivity;
import org.artoolkit.ar.jpct.TrackableObject3d;

import java.util.List;

public class ARActivity extends ArJpctActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        Button finishButton = (Button) findViewById(R.id.finishButton);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMenuScreen();
            }
        });
    }

    private void launchMenuScreen() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Use the FrameLayout in this Activity's UI.
     */
    @Override
    protected FrameLayout supplyFrameLayout() {
        return (FrameLayout)this.findViewById(R.id.mainLayout);
    }

    public void configureWorld(World world) {
        world.setAmbientLight(400, 400, 200);
    }

    protected void populateTrackableObjects(List<TrackableObject3d> list) {
        TrackableObject3d trackablePlane = new TrackableObject3d("single;Data/patt.mark;80", getPlane());
        list.add(trackablePlane);
    }

    private Object3D getPlane() {
        Object3D object3D = Primitives.getPlane(2, 60);
        // Planes are rotated 180 degrees, so we need to flip them
        object3D.rotateX((float) Math.PI);
        // Load the AR Toolkit texture on top of the plane
        Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(ContextCompat.getDrawable(this,R.drawable.ar_image)), 2048, 1024));
        TextureManager.getInstance().addTexture("epsonlogo", texture);

        object3D.setTexture("epsonlogo");
        return object3D;
    }

}
