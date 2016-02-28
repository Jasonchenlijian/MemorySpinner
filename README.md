# MemorySpinner
- 可以记住历史选项的spinner


## Preview ##
![效果图](https://img.alicdn.com/imgextra/i4/181257671/TB2SphCkXXXXXauXpXXXXXXXXXX_!!181257671.gif)


## Gradle ##
	dependencies {
    	compile 'com.clj:memory_spinner:1.0.0'
	}


## Usage ##

- 在xml中像添加官方Spinner一样添加spinner，并设置自己喜欢的风格

	    <com.clj.memoryspinner.MemorySpinner
        	android:id="@+id/ms"
        	android:layout_width="100dp"
        	android:layout_height="50dp"
        	style="@style/Base.Widget.AppCompat.Spinner.Underlined"

        	ms:msDropItemBackgroundColor="@android:color/white"
        	ms:msDropItemText="全部选项"
        	ms:msDropItemTextColor="@android:color/black"
        	ms:msDropItemTextSize="14sp"

        	ms:msDropTitleBackgroundColor="@color/colorPrimary"
        	ms:msDropTitleText="常用选项"
        	ms:msDropTitleTextColor="@android:color/white"
        	ms:msDropTitleTextSize="12sp"

        	ms:msItemTextColor="@android:color/black"
        	ms:msItemTextSize="14sp" />
     

- xml属性说明

		<!-- 默认选项的颜色-->
        <attr name="msItemTextColor" format="reference|color" />
        <!-- 默认选项大小 -->
        <attr name="msItemTextSize" format="reference|dimension" />

        <!-- 下拉标题的背景颜色-->
        <attr name="msDropTitleBackgroundColor" format="reference|color" />
        <!-- 下拉标题字体的颜色-->
        <attr name="msDropTitleTextColor" format="reference|color" />
        <!-- 下拉标题字体大小 -->
        <attr name="msDropTitleTextSize" format="reference|dimension" />
        <!-- 下拉标题文字 -->
        <attr name="msDropTitleText" format="reference|string" />

        <!-- 下拉选项的背景颜色-->
        <attr name="msDropItemBackgroundColor" format="reference|color" />
        <!-- 下拉选项字体的颜色-->
        <attr name="msDropItemTextColor" format="reference|color" />
        <!-- 下拉选项字体大小 -->
        <attr name="msDropItemTextSize" format="reference|dimension" />
        <!-- 下拉选项文字 -->
        <attr name="msDropItemText" format="reference|string" />

- 在代码中给MemorySpinner设置数据

		MemorySpinner memorySpinner = (MemorySpinner) findViewById(R.id.ms);
    	ArrayList<String> list = new ArrayList<>(Arrays.asList("C_1", "C_2", "C_3", "C_4", "C_5",
            	"C_6", "C_7", "C_8", "C_9", "C_10", "C_11", "C_12", "C_13", "C_14"));
    	memorySpinner.setMemoryCount(4);
    	memorySpinner.setData(null, list);

- 方法说明
 
		// 设置memory数量，默认5
		void setMemoryCount(int count) 
		// 设置填充的list，第一项为预设memory内容，可空；第二项为所有内容，不能为空。  
    	void setData(ArrayList<String> prepareList, ArrayList<String> normalList)
