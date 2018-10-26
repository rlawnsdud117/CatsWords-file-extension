package com.example.exts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        String identifier = intent.getExtras().getString("identifier");
        Intent intent1 = getIntent();

        String prefix = intent1.getExtras().getString("prefix");// 뒷자리
        String orgranization = intent1.getExtras().getString("orgranization");// 회사
        String execution = intent1.getExtras().getString("execution");// 실행정보
        String description = intent1.getExtras().getString("description");// 간단 요약설명
        String content =  intent1.getExtras().getString("content");
        String opewnith =  intent1.getExtras().getString("opewnith");
        String signature = intent1.getExtras().getString("signature");
        String dateitme = intent1.getExtras().getString("dateitme");

        textView.setText("확장자 :"+ identifier+ " 분류 : " +prefix +""+ execution+""+ orgranization+""+description+""+content+""+opewnith +""+signature+""+dateitme +"");

        ///        GetXMLTask XMLURL = new GetXMLTask();
   //     XMLURL.execute("https://exts.kr/api/claw.php?q=" + K);
  }

}
