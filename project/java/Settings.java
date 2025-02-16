/*
Simple DirectMedia Layer
Java source code (C) 2009-2014 Sergii Pylypenko

This software is provided 'as-is', without any express or implied
warranty.  In no event will the authors be held liable for any damages
arising from the use of this software.

Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it
freely, subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not
   claim that you wrote the original software. If you use this software
   in a product, an acknowledgment in the product documentation would be
   appreciated but is not required. 
2. Altered source versions must be plainly marked as such, and must not be
   misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
*/

package net.sourceforge.clonekeenplus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.util.Log;
import java.io.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.StatFs;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.Collections;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.lang.String;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;
import android.text.Editable;
import android.text.SpannedString;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.util.DisplayMetrics;
import android.net.Uri;
import java.util.concurrent.Semaphore;
import java.util.Arrays;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.os.Build;
import android.content.Intent;


// TODO: too much code here, split into multiple files, possibly auto-generated menus?
public class Settings
{
	static String SettingsFileName = "libsdl-settings.cfg";

	static boolean settingsLoaded = false;
	static boolean settingsChanged = false;
	static final int SETTINGS_FILE_VERSION = 5;
	static boolean convertButtonSizeFromOldSdlVersion = false;

	static void Save(final MainActivity p)
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(p.openFileOutput( SettingsFileName, p.MODE_PRIVATE ));
			out.writeInt(SETTINGS_FILE_VERSION);
			out.writeBoolean(Globals.DownloadToSdcard);
			out.writeBoolean(Globals.PhoneHasArrowKeys);
			out.writeBoolean(false);
			out.writeBoolean(Globals.UseAccelerometerAsArrowKeys);
			out.writeBoolean(Globals.UseTouchscreenKeyboard);
			out.writeInt(Globals.TouchscreenKeyboardSize);
			out.writeInt(Globals.AccelerometerSensitivity);
			out.writeInt(Globals.AccelerometerCenterPos);
			out.writeInt(0);
			out.writeInt(Globals.AudioBufferConfig);
			out.writeInt(Globals.TouchscreenKeyboardTheme);
			out.writeInt(Globals.RightClickMethod);
			out.writeInt(Globals.ShowScreenUnderFinger);
			out.writeInt(Globals.LeftClickMethod);
			out.writeBoolean(Globals.MoveMouseWithJoystick);
			out.writeBoolean(Globals.ClickMouseWithDpad);
			out.writeInt(Globals.ClickScreenPressure);
			out.writeInt(Globals.ClickScreenTouchspotSize);
			out.writeBoolean(Globals.KeepAspectRatio);
			out.writeInt(Globals.MoveMouseWithJoystickSpeed);
			out.writeInt(Globals.MoveMouseWithJoystickAccel);
			out.writeInt(SDL_Keys.JAVA_KEYCODE_LAST);
			for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
			{
				out.writeInt(Globals.RemapHwKeycode[i]);
			}
			out.writeInt(Globals.RemapScreenKbKeycode.length);
			for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
			{
				out.writeInt(Globals.RemapScreenKbKeycode[i]);
			}
			out.writeInt(Globals.ScreenKbControlsShown.length);
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
			{
				out.writeBoolean(Globals.ScreenKbControlsShown[i]);
			}
			out.writeInt(Globals.TouchscreenKeyboardTransparency);
			out.writeInt(Globals.RemapMultitouchGestureKeycode.length);
			for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			{
				out.writeInt(Globals.RemapMultitouchGestureKeycode[i]);
				out.writeBoolean(Globals.MultitouchGesturesUsed[i]);
			}
			out.writeInt(Globals.MultitouchGestureSensitivity);
			for( int i = 0; i < Globals.TouchscreenCalibration.length; i++ )
				out.writeInt(Globals.TouchscreenCalibration[i]);
			out.writeInt(Globals.DataDir.length());
			for( int i = 0; i < Globals.DataDir.length(); i++ )
				out.writeChar(Globals.DataDir.charAt(i));
			out.writeInt(Globals.CommandLine.length());
			for( int i = 0; i < Globals.CommandLine.length(); i++ )
				out.writeChar(Globals.CommandLine.charAt(i));
			out.writeInt(Globals.ScreenKbControlsLayout.length);
			for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
				for( int ii = 0; ii < 4; ii++ )
					out.writeInt(Globals.ScreenKbControlsLayout[i][ii]);
			out.writeInt(Globals.LeftClickKey);
			out.writeInt(Globals.RightClickKey);
			out.writeBoolean(Globals.VideoLinearFilter);
			out.writeInt(Globals.LeftClickTimeout);
			out.writeInt(Globals.RightClickTimeout);
			out.writeBoolean(Globals.RelativeMouseMovement);
			out.writeInt(Globals.RelativeMouseMovementSpeed);
			out.writeInt(Globals.RelativeMouseMovementAccel);
			out.writeBoolean(Globals.MultiThreadedVideo);

			out.writeInt(Globals.OptionalDataDownload.length);
			for(int i = 0; i < Globals.OptionalDataDownload.length; i++)
				out.writeBoolean(Globals.OptionalDataDownload[i]);
			out.writeBoolean(false); // Unused
			out.writeInt(Globals.TouchscreenKeyboardDrawSize);
			out.writeInt(p.getApplicationVersion());
			// Gyroscope calibration data, now unused
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);
			out.writeFloat(0.0f);

			out.writeBoolean(Globals.OuyaEmulation);
			out.writeBoolean(Globals.HoverJitterFilter);
			out.writeBoolean(Globals.MoveMouseWithGyroscope);
			out.writeInt(Globals.MoveMouseWithGyroscopeSpeed);
			out.writeBoolean(Globals.FingerHover);
			out.writeBoolean(Globals.FloatingScreenJoystick);
			out.writeBoolean(Globals.GenerateSubframeTouchEvents);
			out.writeInt(Globals.VideoDepthBpp);
			out.writeBoolean(Globals.HorizontalOrientation);
			out.writeBoolean(Globals.ImmersiveMode);
			out.writeBoolean(Globals.AutoDetectOrientation);
			out.writeBoolean(Globals.TvBorders);
			out.writeBoolean(Globals.ForceHardwareMouse);
			convertButtonSizeFromOldSdlVersion = false;
			out.writeBoolean(convertButtonSizeFromOldSdlVersion);

			out.close();
			settingsLoaded = true;
			
		} catch( FileNotFoundException e ) {
		} catch( SecurityException e ) {
		} catch ( IOException e ) {};
	}

	static void Load( final MainActivity p )
	{
		if(settingsLoaded) // Prevent starting twice
		{
			return;
		}
		Log.i("SDL", "libSDL: Settings.Load(): enter");
		nativeInitKeymap();
		for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
		{
			int sdlKey = nativeGetKeymapKey(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapHwKeycode[i] = idx;
		}
		for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
		{
			int sdlKey = nativeGetKeymapKeyScreenKb(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapScreenKbKeycode[i] = idx;
		}
		Globals.ScreenKbControlsShown[0] = (Globals.AppNeedsArrowKeys || Globals.AppUsesJoystick);
		Globals.ScreenKbControlsShown[1] = Globals.AppNeedsTextInput;
		for( int i = 2; i < Globals.ScreenKbControlsShown.length; i++ )
			Globals.ScreenKbControlsShown[i] = ( i - 2 < Globals.AppTouchscreenKeyboardKeysAmount );
		if( Globals.AppUsesSecondJoystick )
			Globals.ScreenKbControlsShown[8] = true;
		if( Globals.AppUsesThirdJoystick )
			Globals.ScreenKbControlsShown[9] = true;
		for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
		{
			int sdlKey = nativeGetKeymapKeyMultitouchGesture(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapMultitouchGestureKeycode[i] = idx;
		}
		for( int i = 0; i < Globals.MultitouchGesturesUsed.length; i++ )
			Globals.MultitouchGesturesUsed[i] = true;
		// Adjust coordinates of on-screen buttons from 800x480
		int displayX = 800;
		int displayY = 480;
		try {
			DisplayMetrics dm = new DisplayMetrics();
			p.getWindowManager().getDefaultDisplay().getMetrics(dm);
			displayX = dm.widthPixels;
			displayY = dm.heightPixels;
		} catch (Exception eeeee) {}
		for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
		{
			Globals.ScreenKbControlsLayout[i][0] *= (float)displayX / 800.0f;
			Globals.ScreenKbControlsLayout[i][2] *= (float)displayX / 800.0f;
			Globals.ScreenKbControlsLayout[i][1] *= (float)displayY / 480.0f;
			Globals.ScreenKbControlsLayout[i][3] *= (float)displayY / 480.0f;
			// Make them square
			int wh = Math.min( Globals.ScreenKbControlsLayout[i][2] - Globals.ScreenKbControlsLayout[i][0], Globals.ScreenKbControlsLayout[i][3] - Globals.ScreenKbControlsLayout[i][1] );
			Globals.ScreenKbControlsLayout[i][2] = Globals.ScreenKbControlsLayout[i][0] + wh;
			Globals.ScreenKbControlsLayout[i][3] = Globals.ScreenKbControlsLayout[i][1] + wh;
		}

		Log.i("SDL", "android.os.Build.MODEL: " + android.os.Build.MODEL);
		if( (android.os.Build.MODEL.equals("GT-N7000") || android.os.Build.MODEL.equals("SGH-I717"))
			&& android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1 )
		{
			// Samsung Galaxy Note generates a keypress when you hover a stylus over the screen, and that messes up OpenTTD dialogs
			// ICS update sends events in a proper way
			Globals.RemapHwKeycode[112] = SDL_1_2_Keycodes.SDLK_UNKNOWN;
		}
		convertButtonSizeFromOldSdlVersion = false;

		try {
			ObjectInputStream settingsFile = new ObjectInputStream(new FileInputStream( p.getFilesDir().getAbsolutePath() + "/" + SettingsFileName ));
			if( settingsFile.readInt() != SETTINGS_FILE_VERSION )
				throw new IOException();
			Globals.DownloadToSdcard = settingsFile.readBoolean();
			Globals.PhoneHasArrowKeys = settingsFile.readBoolean();
			settingsFile.readBoolean();
			Globals.UseAccelerometerAsArrowKeys = settingsFile.readBoolean();
			Globals.UseTouchscreenKeyboard = settingsFile.readBoolean();
			Globals.TouchscreenKeyboardSize = settingsFile.readInt();
			convertButtonSizeFromOldSdlVersion = true; // Will be changed to false if we read the remainder of the config file
			Globals.AccelerometerSensitivity = settingsFile.readInt();
			Globals.AccelerometerCenterPos = settingsFile.readInt();
			settingsFile.readInt();
			Globals.AudioBufferConfig = settingsFile.readInt();
			Globals.TouchscreenKeyboardTheme = settingsFile.readInt();
			Globals.RightClickMethod = settingsFile.readInt();
			Globals.ShowScreenUnderFinger = settingsFile.readInt();
			Globals.LeftClickMethod = settingsFile.readInt();
			Globals.MoveMouseWithJoystick = settingsFile.readBoolean();
			Globals.ClickMouseWithDpad = settingsFile.readBoolean();
			Globals.ClickScreenPressure = settingsFile.readInt();
			Globals.ClickScreenTouchspotSize = settingsFile.readInt();
			Globals.KeepAspectRatio = settingsFile.readBoolean();
			Globals.MoveMouseWithJoystickSpeed = settingsFile.readInt();
			Globals.MoveMouseWithJoystickAccel = settingsFile.readInt();
			int readKeys = settingsFile.readInt();
			for( int i = 0; i < readKeys; i++ )
			{
				Globals.RemapHwKeycode[i] = settingsFile.readInt();
			}
			if( settingsFile.readInt() != Globals.RemapScreenKbKeycode.length )
				throw new IOException();
			for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
			{
				Globals.RemapScreenKbKeycode[i] = settingsFile.readInt();
			}
			if( settingsFile.readInt() != Globals.ScreenKbControlsShown.length )
				throw new IOException();
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
			{
				Globals.ScreenKbControlsShown[i] = settingsFile.readBoolean();
			}
			Globals.TouchscreenKeyboardTransparency = settingsFile.readInt();
			if( settingsFile.readInt() != Globals.RemapMultitouchGestureKeycode.length )
				throw new IOException();
			for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			{
				Globals.RemapMultitouchGestureKeycode[i] = settingsFile.readInt();
				Globals.MultitouchGesturesUsed[i] = settingsFile.readBoolean();
			}
			Globals.MultitouchGestureSensitivity = settingsFile.readInt();
			for( int i = 0; i < Globals.TouchscreenCalibration.length; i++ )
				Globals.TouchscreenCalibration[i] = settingsFile.readInt();
			StringBuilder b = new StringBuilder();
			int len = settingsFile.readInt();
			for( int i = 0; i < len; i++ )
				b.append( settingsFile.readChar() );
			Globals.DataDir = b.toString();

			b = new StringBuilder();
			len = settingsFile.readInt();
			for( int i = 0; i < len; i++ )
				b.append( settingsFile.readChar() );
			Globals.CommandLine = b.toString();

			if( settingsFile.readInt() != Globals.ScreenKbControlsLayout.length )
				throw new IOException();
			for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
				for( int ii = 0; ii < 4; ii++ )
					Globals.ScreenKbControlsLayout[i][ii] = settingsFile.readInt();
			Globals.LeftClickKey = settingsFile.readInt();
			Globals.RightClickKey = settingsFile.readInt();
			Globals.VideoLinearFilter = settingsFile.readBoolean();
			Globals.LeftClickTimeout = settingsFile.readInt();
			Globals.RightClickTimeout = settingsFile.readInt();
			Globals.RelativeMouseMovement = settingsFile.readBoolean();
			Globals.RelativeMouseMovementSpeed = settingsFile.readInt();
			Globals.RelativeMouseMovementAccel = settingsFile.readInt();
			Globals.MultiThreadedVideo = settingsFile.readBoolean();

			Globals.OptionalDataDownload = new boolean[settingsFile.readInt()];
			for(int i = 0; i < Globals.OptionalDataDownload.length; i++)
				Globals.OptionalDataDownload[i] = settingsFile.readBoolean();
			settingsFile.readBoolean(); // Unused
			Globals.TouchscreenKeyboardDrawSize = settingsFile.readInt();
			int cfgVersion = settingsFile.readInt();
			// Gyroscope calibration data, now unused
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();
			settingsFile.readFloat();

			Globals.OuyaEmulation = settingsFile.readBoolean();
			Globals.HoverJitterFilter = settingsFile.readBoolean();
			Globals.MoveMouseWithGyroscope = settingsFile.readBoolean();
			Globals.MoveMouseWithGyroscopeSpeed = settingsFile.readInt();
			Globals.FingerHover = settingsFile.readBoolean();
			Globals.FloatingScreenJoystick = settingsFile.readBoolean();
			Globals.GenerateSubframeTouchEvents = settingsFile.readBoolean();
			Globals.VideoDepthBpp = settingsFile.readInt();
			Globals.HorizontalOrientation = settingsFile.readBoolean();
			Globals.ImmersiveMode = settingsFile.readBoolean();
			Globals.AutoDetectOrientation = settingsFile.readBoolean();
			Globals.TvBorders = settingsFile.readBoolean();
			Globals.ForceHardwareMouse = settingsFile.readBoolean();
			convertButtonSizeFromOldSdlVersion = settingsFile.readBoolean();

			settingsLoaded = true;

			Log.i("SDL", "libSDL: Settings.Load(): loaded settings successfully");
			settingsFile.close();

			Log.i("SDL", "libSDL: old cfg version " + cfgVersion + ", our version " + p.getApplicationVersion());
			if( cfgVersion != p.getApplicationVersion() )
			{
				DeleteFilesOnUpgrade(p);
				if( Globals.ResetSdlConfigForThisVersion )
				{
					Log.i("SDL", "libSDL: old cfg version " + cfgVersion + ", our version " + p.getApplicationVersion() + " and we need to clean up config file");
					// Delete settings file, and restart the application
					DeleteSdlConfigOnUpgradeAndRestart(p);
				}
				Save(p);
			}

			return;
			
		} catch( FileNotFoundException e ) {
			Log.i("SDL", "libSDL: settings file not found: " + e);
		} catch( SecurityException e ) {
			Log.i("SDL", "libSDL: settings file cannot be opened: " + e);
		} catch( IOException e ) {
			Log.i("SDL", "libSDL: settings file cannot be read: " + e);
			DeleteFilesOnUpgrade(p);
			if (convertButtonSizeFromOldSdlVersion && Globals.TouchscreenKeyboardSize + 1 < Globals.TOUCHSCREEN_KEYBOARD_CUSTOM)
			{
				Globals.TouchscreenKeyboardSize ++; // New default button size is bigger, but we are keeping old button size for existing installations
				//if (Globals.AppTouchscreenKeyboardKeysAmount <= 4 && Globals.TouchscreenKeyboardSize + 1 < Globals.TOUCHSCREEN_KEYBOARD_CUSTOM)
				//	Globals.TouchscreenKeyboardSize ++; // If there are only 4 buttons they are even bigger
			}
			if( Globals.ResetSdlConfigForThisVersion )
			{
				Log.i("SDL", "libSDL: old cfg version unknown or too old, our version " + p.getApplicationVersion() + " and we need to clean up config file");
				DeleteSdlConfigOnUpgradeAndRestart(p);
			}
		};
		
		if( Globals.DataDir.length() == 0 )
		{
			if( !Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) )
			{
				Log.i("SDL", "libSDL: SD card or external storage is not mounted (state " + Environment.getExternalStorageState() + "), switching to the internal storage.");
				Globals.DownloadToSdcard = false;
			}
			if( p.getPackageManager().checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, p.getPackageName()) != PackageManager.PERMISSION_GRANTED &&
				android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT )
			{
				Log.i("SDL", "libSDL: We don't have permission to write to SD card, switching to the internal storage.");
				Globals.DownloadToSdcard = false;
			}
			Globals.DataDir = Globals.DownloadToSdcard ?
								SdcardAppPath.get().bestPath(p) :
								p.getFilesDir().getAbsolutePath();
			if( Globals.DownloadToSdcard )
			{
				// Check if data already installed into deprecated location at /sdcard/app-data/<package-name>
				String[] fileList = new File(SdcardAppPath.deprecatedPath(p)).list();
				if( fileList != null )
					for( String s: fileList )
						if( s.toUpperCase().startsWith(DataDownloader.DOWNLOAD_FLAG_FILENAME.toUpperCase()) )
							Globals.DataDir = SdcardAppPath.deprecatedPath(p);
				// Also check for pre-Kitkat files location
				fileList = new File(SdcardAppPath.get().path(p)).list();
				if( fileList != null )
					for( String s: fileList )
						if( s.toUpperCase().startsWith(DataDownloader.DOWNLOAD_FLAG_FILENAME.toUpperCase()) )
							Globals.DataDir = SdcardAppPath.get().path(p);

				try {
					new File(Globals.DataDir).mkdirs();
					new FileOutputStream( new File(Globals.DataDir, ".nomedia") ).close();
				} catch (Exception e) {
					Log.i("SDL", "libSDL: cannot create .nomedia file at " + Globals.DataDir + " - switching to internal storage");
					Globals.DownloadToSdcard = false; // SD card not writable
					Globals.DataDir = p.getFilesDir().getAbsolutePath();
				}
			}
		}

		Log.i("SDL", "libSDL: Settings.Load(): loading settings failed, running config dialog");
		p.setScreenOrientation();
		p.setUpStatusLabel();
		if( checkRamSize(p) )
			SettingsMenu.showConfig(p, true);
	}

	// ===============================================================================================

	public static boolean deleteRecursively(File dir)
	{
		boolean success = true;
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++)
			{
				if (!deleteRecursively(new File(dir, children[i])))
					success = false;
			}
		}
		if (!dir.delete())
			success = false;
		return success;
	}
	public static boolean deleteRecursivelyAndLog(File dir)
	{
		boolean success = true;
		Log.v("SDL", "Deleting old file: " + dir.getAbsolutePath() + " exists " + dir.exists());
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++)
			{
				if (!deleteRecursively(new File(dir, children[i])))
					success = false;
			}
		}
		if (!dir.delete())
			success = false;
		return success;
	}
	public static void DeleteFilesOnUpgrade(final MainActivity p)
	{
		String [] files = Globals.DeleteFilesOnUpgrade.split(" ");
		for(String path: files)
		{
			if( path.equals("") )
				continue;
			deleteRecursivelyAndLog(new File( p.getFilesDir().getAbsolutePath() + "/" + path ));
			for( String sdpath: SdcardAppPath.get().allPaths(p) )
				deleteRecursivelyAndLog(new File(sdpath + "/" + path ));
		}
	}
	public static void DeleteSdlConfigOnUpgradeAndRestart(final MainActivity p)
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(p.openFileOutput( SettingsFileName, p.MODE_PRIVATE ));
			out.writeInt(-1);
			out.close();
		} catch( FileNotFoundException e ) {
		} catch ( IOException e ) { }
		new File( p.getFilesDir() + "/" + SettingsFileName ).delete();

		Intent intent = new Intent(p, RestartMainActivity.class);
		p.startActivity(intent);
		System.exit(0);
	}

	// ===============================================================================================

	static void applyMouseEmulationOptions()
	{
		if( Globals.AppUsesMouse )
			nativeSetMouseUsed( Globals.RightClickMethod,
								Globals.ShowScreenUnderFinger,
								Globals.LeftClickMethod,
								Globals.MoveMouseWithJoystick ? 1 : 0,
								Globals.ClickMouseWithDpad ? 1 : 0,
								Globals.ClickScreenPressure,
								Globals.ClickScreenTouchspotSize,
								Globals.MoveMouseWithJoystickSpeed,
								Globals.MoveMouseWithJoystickAccel,
								Globals.LeftClickKey,
								Globals.RightClickKey,
								Globals.LeftClickTimeout,
								Globals.RightClickTimeout,
								Globals.RelativeMouseMovement ? 1 : 0,
								Globals.RelativeMouseMovementSpeed,
								Globals.RelativeMouseMovementAccel,
								Globals.ShowMouseCursor ? 1 : 0,
								Globals.HoverJitterFilter ? 1 : 0,
								Globals.RightMouseButtonLongPress ? 1 : 0,
								Globals.MoveMouseWithGyroscope ? 1 : 0,
								Globals.MoveMouseWithGyroscopeSpeed,
								Globals.CompatibilityHacksForceScreenUpdateMouseClick ? 1 : 0,
								Globals.ScreenFollowsMouse ? 1 : 0 );
	}

	static void Apply(MainActivity p)
	{
		setEnvVars(p);
		nativeSetVideoDepth(Globals.VideoDepthBpp, Globals.NeedGles2 ? 1 : 0, Globals.NeedGles3 ? 1 : 0);
		if(Globals.VideoLinearFilter)
			nativeSetVideoLinearFilter();
		if( Globals.CompatibilityHacksVideo )
		{
			Globals.MultiThreadedVideo = true;
			Globals.SwVideoMode = true;
			nativeSetCompatibilityHacks();
		}
		if( Globals.SwVideoMode )
			nativeSetVideoForceSoftwareMode();
		if( Globals.SwVideoMode && Globals.MultiThreadedVideo )
			nativeSetVideoMultithreaded();
		applyMouseEmulationOptions();
		nativeSetJoystickUsed( Globals.AppUsesThirdJoystick ? 3 : (Globals.AppUsesSecondJoystick ? 2 : (Globals.AppUsesJoystick ? 1 : 0)) );
		if( Globals.AppUsesAccelerometer )
			nativeSetAccelerometerUsed();
		if( Globals.AppUsesMultitouch )
			nativeSetMultitouchUsed();
		nativeSetAccelerometerSettings(Globals.AccelerometerSensitivity, Globals.AccelerometerCenterPos);
		if( Globals.UseTouchscreenKeyboard )
		{
			boolean screenKbReallyUsed = false;
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
				if( Globals.ScreenKbControlsShown[i] )
					screenKbReallyUsed = true;
			if( p.isRunningOnOUYA() )
				screenKbReallyUsed = false;
			if( screenKbReallyUsed )
			{
				nativeSetTouchscreenKeyboardUsed();
				nativeSetupScreenKeyboard(	Globals.TouchscreenKeyboardSize,
											Globals.TouchscreenKeyboardDrawSize,
											Globals.TouchscreenKeyboardTheme,
											Globals.TouchscreenKeyboardTransparency,
											Globals.FloatingScreenJoystick ? 1 : 0,
											Globals.AppTouchscreenKeyboardKeysAmount );
				SetupTouchscreenKeyboardGraphics(p);
				for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
					nativeSetKeymapKeyScreenKb(i, SDL_Keys.values[Globals.RemapScreenKbKeycode[i]]);
				if( Globals.TouchscreenKeyboardSize == Globals.TOUCHSCREEN_KEYBOARD_CUSTOM )
				{
					for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
						if( Globals.ScreenKbControlsLayout[i][0] < Globals.ScreenKbControlsLayout[i][2] )
							nativeSetScreenKbKeyLayout( i, Globals.ScreenKbControlsLayout[i][0], Globals.ScreenKbControlsLayout[i][1],
								Globals.ScreenKbControlsLayout[i][2], Globals.ScreenKbControlsLayout[i][3]);
				}
				for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
					nativeSetScreenKbKeyUsed(i, Globals.ScreenKbControlsShown[i] ? 1 : 0);
			}
			else
				Globals.UseTouchscreenKeyboard = false;
		}

		for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
			nativeSetKeymapKey(i, SDL_Keys.values[Globals.RemapHwKeycode[i]]);
		for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			nativeSetKeymapKeyMultitouchGesture(i, Globals.MultitouchGesturesUsed[i] ? SDL_Keys.values[Globals.RemapMultitouchGestureKeycode[i]] : 0);
		nativeSetMultitouchGestureSensitivity(Globals.MultitouchGestureSensitivity);
		if( Globals.TouchscreenCalibration[2] > Globals.TouchscreenCalibration[0] )
			nativeSetTouchscreenCalibration(Globals.TouchscreenCalibration[0], Globals.TouchscreenCalibration[1],
				Globals.TouchscreenCalibration[2], Globals.TouchscreenCalibration[3]);
	}

	static void setEnvVars(MainActivity p)
	{
		String lang = new String(Locale.getDefault().getLanguage());
		if( Locale.getDefault().getCountry().length() > 0 )
			lang = lang + "_" + Locale.getDefault().getCountry();
		Log.i("SDL",  "libSDL: setting envvar LANGUAGE to '" + lang + "'");
		nativeSetEnv( "LANG", lang );
		nativeSetEnv( "LANGUAGE", lang );
		// TODO: get current user name and set envvar USER, the API is not availalbe on Android 1.6 so I don't bother with this
		nativeSetEnv( "APPDIR", p.getFilesDir().getAbsolutePath() );
		nativeSetEnv( "SECURE_STORAGE_DIR", p.getFilesDir().getAbsolutePath() );
		nativeSetEnv( "LIBDIR", p.getApplicationInfo().nativeLibraryDir );
		nativeSetEnv( "DATADIR", Globals.DataDir );
		nativeSetEnv( "UNSECURE_STORAGE_DIR", Globals.DataDir );
		SdcardAppPath.get().setEnv(p);
		nativeSetEnv( "HOME", Globals.DataDir );
		nativeSetEnv( "SDCARD", Environment.getExternalStorageDirectory().getAbsolutePath() );
		nativeSetEnv( "SDCARD_DOWNLOADS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() );
		nativeSetEnv( "SDCARD_PICTURES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() );
		nativeSetEnv( "SDCARD_MOVIES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath() );
		nativeSetEnv( "SDCARD_DCIM", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() );
		nativeSetEnv( "SDCARD_MUSIC", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath() );
		nativeSetEnv( "ANDROID_VERSION", String.valueOf(android.os.Build.VERSION.SDK_INT) );
		nativeSetEnv( "ANDROID_PACKAGE_NAME", p.getPackageName() );
		nativeSetEnv( "ANDROID_PACKAGE_PATH", p.getPackageCodePath() );
		nativeSetEnv( "ANDROID_MY_OWN_APP_FILE", p.getPackageResourcePath() ); // This may be different from p.getPackageCodePath() on multi-user systems, but should still be the same .apk file
		nativeSetEnv( "ANDROID_OBB_DIR", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + p.getPackageName() );
		try {
			nativeSetEnv( "ANDROID_OBB_DIR", p.getObbDir().getAbsolutePath() );
		} catch (Exception eeeeeee) {}
		try {
			nativeSetEnv( "ANDROID_APP_NAME", p.getString(p.getApplicationInfo().labelRes) );
		} catch (Exception eeeeee) {}
		Log.d("SDL", "libSDL: Is running on OUYA: " + p.isRunningOnOUYA());
		if( p.isRunningOnOUYA() )
		{
			nativeSetEnv( "OUYA", "1" );
			nativeSetEnv( "TV", "1" );
			nativeSetEnv( "ANDROID_TV", "1" );
		}
		if (p.getIntent().getCategories() != null && p.getIntent().getCategories().contains("com.google.intent.category.CARDBOARD")) {
			nativeSetEnv( "CARDBOARD", "1" );
			nativeSetEnv( "VR", "1" );
			nativeSetEnv( "CARDBOARD_VR", "1" );
		}
		if (p.getIntent().getStringExtra(RestartMainActivity.SDL_RESTART_PARAMS) != null)
			nativeSetEnv( RestartMainActivity.SDL_RESTART_PARAMS, p.getIntent().getStringExtra(RestartMainActivity.SDL_RESTART_PARAMS) );
		try {
			DisplayMetrics dm = new DisplayMetrics();
			p.getWindowManager().getDefaultDisplay().getMetrics(dm);
			float xx = dm.widthPixels/dm.xdpi;
			float yy = dm.heightPixels/dm.ydpi;
			float x = Math.max(xx, yy);
			float y = Math.min(xx, yy);
			float displayInches = (float)Math.sqrt( x*x + y*y );
			nativeSetEnv( "DISPLAY_SIZE", String.valueOf(displayInches) );
			nativeSetEnv( "DISPLAY_SIZE_MM", String.valueOf((int)(displayInches*25.4f)) );
			nativeSetEnv( "DISPLAY_WIDTH", String.valueOf(x) );
			nativeSetEnv( "DISPLAY_HEIGHT", String.valueOf(y) );
			nativeSetEnv( "DISPLAY_WIDTH_MM", String.valueOf((int)(x*25.4f)) );
			nativeSetEnv( "DISPLAY_HEIGHT_MM", String.valueOf((int)(y*25.4f)) );
			nativeSetEnv( "DISPLAY_RESOLUTION_WIDTH", String.valueOf(Math.max(dm.widthPixels, dm.heightPixels)) );
			nativeSetEnv( "DISPLAY_RESOLUTION_HEIGHT", String.valueOf(Math.min(dm.widthPixels, dm.heightPixels)) );
		} catch (Exception eeeee) {}
	}

	static byte [] loadRaw(Activity p, int res)
	{
		byte [] buf = new byte[65536 * 2];
		byte [] a = new byte[1048576 * 5]; // We need 5Mb buffer for Keen theme, and this Java code is inefficient
		int written = 0;
		try{
			InputStream is = new GZIPInputStream(p.getResources().openRawResource(res));
			int readed = 0;
			while( (readed = is.read(buf)) >= 0 )
			{
				if( written + readed > a.length )
				{
					byte [] b = new byte [written + readed];
					System.arraycopy(a, 0, b, 0, written);
					a = b;
				}
				System.arraycopy(buf, 0, a, written, readed);
				written += readed;
			}
		} catch(Exception e) {};
		byte [] b = new byte [written];
		System.arraycopy(a, 0, b, 0, written);
		return b;
	}
	
	static void SetupTouchscreenKeyboardGraphics(Activity p)
	{
		if( Globals.UseTouchscreenKeyboard )
		{
			if(Globals.TouchscreenKeyboardTheme < 0)
				Globals.TouchscreenKeyboardTheme = 0;
			if(Globals.TouchscreenKeyboardTheme > 9)
				Globals.TouchscreenKeyboardTheme = 9;

			if( Globals.TouchscreenKeyboardTheme == 0 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.ultimatedroid));
			if( Globals.TouchscreenKeyboardTheme == 1 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.simpletheme));
			if( Globals.TouchscreenKeyboardTheme == 2 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.sun));
			if( Globals.TouchscreenKeyboardTheme == 3 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.keen));
			if( Globals.TouchscreenKeyboardTheme == 4 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.retro));
			if( Globals.TouchscreenKeyboardTheme == 5 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.gba));
			if( Globals.TouchscreenKeyboardTheme == 6 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.psx));
			if( Globals.TouchscreenKeyboardTheme == 7 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.snes));
			if( Globals.TouchscreenKeyboardTheme == 8 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.dualshock));
			if( Globals.TouchscreenKeyboardTheme == 9 )
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.n64));
		}
	}

	abstract static class SdcardAppPath
	{
		public static SdcardAppPath get()
		{
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
				return Kitkat.Holder.sInstance;
			else
				return Froyo.Holder.sInstance;
		}
		public String path(final Context p)
		{
			return get().path(p);
		}
		public void setEnv(final Context p)
		{
			get().setEnv(p);
		}
		public String bestPath(final Context p)
		{
			return get().bestPath(p);
		};
		public String[] allPaths(final Context p)
		{
			return get().allPaths(p);
		};
		public static final String deprecatedPath(final Context p)
		{
			return Environment.getExternalStorageDirectory().getAbsolutePath() + "/app-data/" + p.getPackageName();
		}

		private static class Froyo extends SdcardAppPath
		{
			private static class Holder
			{
				private static final Froyo sInstance = new Froyo();
			}
			@Override
			public String path(final Context p)
			{
				if( p.getExternalFilesDir(null) == null )
				{
					if( Environment.getExternalStorageDirectory() == null )
						return "/sdcard/Android/data/" + p.getPackageName() + "/files";
					return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + p.getPackageName() + "/files";
				}
				return p.getExternalFilesDir(null).getAbsolutePath();
			}
			@Override
			public void setEnv(final Context p)
			{
				nativeSetEnv( "UNSECURE_STORAGE_DIR_0", Globals.DataDir );
			}
			@Override
			public String bestPath(final Context p)
			{
				return path(p);
			}
			@Override
			public String[] allPaths(final Context p)
			{
				return new String[] { path(p), deprecatedPath(p) };
			}
		}
		private static class Kitkat extends Froyo
		{
			private static class Holder
			{
				private static final Kitkat sInstance = new Kitkat();
			}
			@Override
			public String bestPath(final Context p)
			{
				File[] paths = p.getExternalFilesDirs(null);
				String ret = path(p);
				long maxSize = -1;
				for( File path: paths )
				{
					if( path == null )
						continue;
					long size = -1;
					try {
						StatFs stat = new StatFs(path.getPath());
						size = (long)stat.getAvailableBlocks() * stat.getBlockSize() / 1024 / 1024;
					} catch (Exception ee) {} // Can throw an exception if we cannot read from SD card

					try {
						path.mkdirs();
						new FileOutputStream( new File(path, ".nomedia") ).close();
					} catch (Exception e) {
						size = -1; // Not writable
					}

					if( size > maxSize )
					{
						maxSize = size;
						ret = path.getAbsolutePath();
					}
				}
				return ret;
			};
			@Override
			public void setEnv(final Context p)
			{
				File[] paths = p.getExternalFilesDirs(null);
				int index = 0;
				for( File path: paths )
				{
					if( path == null )
						continue;
					if( !path.exists() )
						path.mkdirs();
					nativeSetEnv( "UNSECURE_STORAGE_DIR_" + index, path.getAbsolutePath() );
					index++;
				}
			}
			@Override
			public String[] allPaths(final Context p)
			{
				ArrayList<String> ret = new ArrayList<String>();
				for( File path: p.getExternalFilesDirs(null) )
				{
					if( path == null )
						continue;
					try {
						path.mkdirs();
						new FileOutputStream( new File(path, ".nomedia") ).close();
					} catch (Exception e) {
						continue;
					}
					ret.add(path.getAbsolutePath());
				}
				ret.add(deprecatedPath(p));
				return ret.toArray(new String[0]);
			}
		}
	}
	
	static boolean checkRamSize(final MainActivity p)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/proc/meminfo"));
			String line = null;
			while( ( line = reader.readLine() ) != null )
			{
				if( line.indexOf("MemTotal:") == 0 )
				{
					String[] fields = line.split("[ \t]+");
					Long size = Long.parseLong(fields[1]);
					Log.i("SDL", "Device RAM size: " + size / 1024 + " Mb, required minimum RAM: " + Globals.AppMinimumRAM + " Mb" );
					if( size / 1024 < Globals.AppMinimumRAM )
					{
						settingsChanged = true;
						AlertDialog.Builder builder = new AlertDialog.Builder(p);
						builder.setTitle(R.string.not_enough_ram);
						builder.setMessage(p.getResources().getString( R.string.not_enough_ram_size, Globals.AppMinimumRAM, (int)(size / 1024)) );
						builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int item)
							{
								p.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + p.getPackageName())));
								System.exit(0);
							}
						});
						builder.setNegativeButton(p.getResources().getString(R.string.ignore), new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int item)
							{
								SettingsMenu.showConfig(p, true);
								return;
							}
						});
						builder.setOnCancelListener(new DialogInterface.OnCancelListener()
						{
							public void onCancel(DialogInterface dialog)
							{
								p.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + p.getPackageName())));
								System.exit(0);
							}
						});
						final AlertDialog alert = builder.create();
						alert.setOwnerActivity(p);
						alert.show();
						return false;
					}
				}
			}
		} catch ( Exception e ) {
			Log.i("SDL", "Error: cannot parse /proc/meminfo: " + e.toString());
		}
		return true;
	}

	static final int SDL_ANDROID_CONFIG_VIDEO_DEPTH_BPP = 0;

	public static void setConfigOptionFromSDL(int option, int value)
	{
		switch (option)
		{
			case SDL_ANDROID_CONFIG_VIDEO_DEPTH_BPP:
				Globals.VideoDepthBpp = value;
				break;
			default:
				Log.e("SDL", "setConfigOptionFromSDL: cannot find option with ID " + option + ", value " + value);
				break;
		}
		Save(MainActivity.instance);
	}

	private static native void nativeSetAccelerometerSettings(int sensitivity, int centerPos);
	private static native void nativeSetMouseUsed(int RightClickMethod, int ShowScreenUnderFinger, int LeftClickMethod, 
													int MoveMouseWithJoystick, int ClickMouseWithDpad, int MaxForce, int MaxRadius,
													int MoveMouseWithJoystickSpeed, int MoveMouseWithJoystickAccel,
													int leftClickKeycode, int rightClickKeycode,
													int leftClickTimeout, int rightClickTimeout,
													int relativeMovement, int relativeMovementSpeed,
													int relativeMovementAccel, int showMouseCursor,
													int HoverJitterFilter, int RightMouseButtonLongPress,
													int MoveMouseWithGyroscope, int MoveMouseWithGyroscopeSpeed,
													int ForceScreenUpdateMouseClick, int ScreenFollowsMouse);
	private static native void nativeSetJoystickUsed(int amount);
	private static native void nativeSetAccelerometerUsed();
	private static native void nativeSetMultitouchUsed();
	private static native void nativeSetTouchscreenKeyboardUsed();
	private static native void nativeSetVideoLinearFilter();
	private static native void nativeSetVideoDepth(int bpp, int gles2, int gles3);
	private static native void nativeSetCompatibilityHacks();
	private static native void nativeSetVideoMultithreaded();
	private static native void nativeSetVideoForceSoftwareMode();
	public static native void  nativeSetupScreenKeyboard(int size, int drawsize, int theme, int transparency, int floatingScreenJoystick, int buttonAmount);
	private static native void nativeSetupScreenKeyboardButtons(byte[] img);
	private static native void nativeInitKeymap();
	private static native int  nativeGetKeymapKey(int key);
	private static native void nativeSetKeymapKey(int javakey, int key);
	private static native int  nativeGetKeymapKeyScreenKb(int keynum);
	private static native void nativeSetKeymapKeyScreenKb(int keynum, int key);
	private static native void nativeSetScreenKbKeyUsed(int keynum, int used);
	private static native void nativeSetScreenKbKeyLayout(int keynum, int x1, int y1, int x2, int y2);
	public static native int   nativeGetScreenKeyboardButtonLayout(int button, int coord);
	private static native int  nativeGetKeymapKeyMultitouchGesture(int keynum);
	private static native void nativeSetKeymapKeyMultitouchGesture(int keynum, int key);
	private static native void nativeSetMultitouchGestureSensitivity(int sensitivity);
	public static native void nativeSetTouchscreenCalibration(int x1, int y1, int x2, int y2);
	public static native void  nativeSetEnv(final String name, final String value);
	public static native int   nativeChmod(final String name, int mode);
	public static native void  nativeChdir(final String dir);
}
