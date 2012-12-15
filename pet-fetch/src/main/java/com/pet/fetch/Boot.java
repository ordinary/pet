package com.pet.fetch;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.paoding.rose.scanning.context.RoseAppContext;

import com.pet.core.utils.ThreadPools;
import com.taobao.api.domain.ItemCat;

public class Boot {
	public static void main(String[] args) {
		RoseAppContext context = new RoseAppContext();
		final TaobaokeFetch fetch = context.getBean(TaobaokeFetch.class);
		ThreadPools.execute(new Runnable(
				) {
			
			@Override
			public void run() {
				fetch.exectue();
				
			}
		});
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		long time = calendar.getTime().getTime()
				- Calendar.getInstance().getTime().getTime();
		fetchItemCats(fetch);
		ThreadPools.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				fetchItemCats(fetch);
			}
		}, time, 24 * 3600 * 1000, TimeUnit.MILLISECONDS);
	}

	private static void fetchItemCats(final TaobaokeFetch fetch) {
		List<ItemCat> itemCats = PetClient.getInstance().getItemCats("29", 0);
		for (ItemCat itemCat : itemCats) {
			fetch.addItemCat(itemCat);
		}
	}

}
