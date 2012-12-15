package com.duanluo.search.annotations;

public @interface Search {
	 String indexPath() default "";
	 String similarity() default "";

}
