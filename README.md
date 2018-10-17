# FastFramework
设计的初衷是为了方便开发时抽离公共代码，每次的新建项目都要从老项目中抽取代码移植，很麻烦，所以有了这个想法，初步阶段一步步完善。
如果哪位有兴趣一起完善搭建的可以加我的QQ417460460 

添加 
compileOptions {

        sourceCompatibility JavaVersion.VERSION_1_8

        targetCompatibility JavaVersion.VERSION_1_8

    }


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Cool-Yang:FastFramework:1.0'
	}
	
	
