/**************************************************************************************************
 Filename:       HelpView.java

 Copyright (c) 2013 - 2014 Texas Instruments Incorporated

 All rights reserved not granted herein.
 Limited License.

 Texas Instruments Incorporated grants a world-wide, royalty-free,
 non-exclusive license under copyrights and patents it now or hereafter
 owns or controls to make, have made, use, import, offer to sell and sell ("Utilize")
 this software subject to the terms herein.  With respect to the foregoing patent
 license, such license is granted  solely to the extent that any such patent is necessary
 to Utilize the software alone.  The patent license shall not apply to any combinations which
 include this software, other than combinations with devices manufactured by or for TI ('TI Devices').
 No hardware patent is licensed hereunder.

 Redistributions must preserve existing copyright notices and reproduce this license (including the
 above copyright notice and the disclaimer and (if applicable) source code license limitations below)
 in the documentation and/or other materials provided with the distribution

 Redistribution and use in binary form, without modification, are permitted provided that the following
 conditions are met:

 * No reverse engineering, decompilation, or disassembly of this software is permitted with respect to any
 software provided in binary form.
 * any redistribution and use are licensed by TI for use only with TI Devices.
 * Nothing shall obligate TI to provide you with source code for the software licensed and provided to you in object code.

 If software source code is provided to you, modification and redistribution of the source code are permitted
 provided that the following conditions are met:

 * any redistribution and use of the source code, including any resulting derivative works, are licensed by
 TI for use only with TI Devices.
 * any redistribution and use of any object code compiled from the source code and any resulting derivative
 works, are licensed by TI for use only with TI Devices.

 Neither the name of Texas Instruments Incorporated nor the names of its suppliers may be used to endorse or
 promote products derived from this software without specific prior written permission.

 DISCLAIMER.

 THIS SOFTWARE IS PROVIDED BY TI AND TI'S LICENSORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL TI AND TI'S LICENSORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.


 **************************************************************************************************/
package com.example.ti.ble.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ti.ble.sensortag.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaggingView extends Fragment implements View.OnClickListener {
  //  private String mFile = "about.html";
  private int mIdFragment;
//  private int mIdWebPage;

  public static ArrayList<String> LabelList = new ArrayList<>();
  public static ArrayList<String> LabelTimeList = new ArrayList<>();

  public static TaggingView context_help;

  public TaggingView() {
    mIdFragment = R.layout.fragment_tagging;
  }

//  public void setParameters(String file, int idFragment, int idWebPage) {
//    if (file != null)
//      mFile = file;
//    mIdFragment = idFragment;
////    mIdWebPage = idWebPage;
//  }

  public void setParameters(int idFragment) {
    mIdFragment = idFragment;
//    mIdWebPage = idWebPage;
  }
  TextView timeTv;
  String tagTv;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(mIdFragment, container, false);
//    WebView wv = (WebView) rootView.findViewById(mIdWebPage);
//
//    wv.loadUrl("file:///android_asset/" + mFile);

    context_help = this;

    Button standBtn = rootView.findViewById(R.id.stand_button);
    Button layBtn = rootView.findViewById(R.id.lay_button);
    Button sitBtn = rootView.findViewById(R.id.sit_button);
    Button walkBtn = rootView.findViewById(R.id.walk_button);
    Button slowWalkBtn = rootView.findViewById(R.id.slowWalk_button);
    Button runBtn = rootView.findViewById(R.id.run_button);
    Button eatBtn = rootView.findViewById(R.id.eat_button);
    Button digBtn = rootView.findViewById(R.id.dig_button);
    Button stopBtn = rootView.findViewById(R.id.stop_button);
    timeTv = rootView.findViewById(R.id.time_tv);

    standBtn.setOnClickListener(this);
    layBtn.setOnClickListener(this);
    sitBtn.setOnClickListener(this);
    walkBtn.setOnClickListener(this);
    slowWalkBtn.setOnClickListener(this);
    runBtn.setOnClickListener(this);
    eatBtn.setOnClickListener(this);
    digBtn.setOnClickListener(this);
    stopBtn.setOnClickListener(this);

    return rootView;
  }

  @Override
  public void onClick(View view) {
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat dataFormatTag = new SimpleDateFormat("HHmmss");
    switch (view.getId()) {
      case R.id.stand_button:
        LabelList.add("0");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "서있기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.lay_button:
        LabelList.add("1");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "눕기+엎드리기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.sit_button:
        LabelList.add("2");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "앉기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.walk_button:
        LabelList.add("3");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "걷기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.slowWalk_button:
        LabelList.add("4");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "느리게 걷기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.run_button:
        LabelList.add("5");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "뛰기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.eat_button:
        LabelList.add("6");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "먹기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.dig_button:
        LabelList.add("7");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "파기 / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
      case R.id.stop_button:
        LabelList.add("8");
        LabelTimeList.add(dataFormatTag.format(date));
        tagTv = "STOP / " + dateFormat.format(date);
        timeTv.setText(tagTv);
        break;
    }
  }

}
