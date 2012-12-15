package com.pet.fetch;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pet.core.utils.ThreadPools;
import com.taobao.api.domain.ItemCat;

@Component
public class TaobaokeFetch {

	@Autowired
	private FetchItemDealwith fetchItemDealwith;

	private final Queue<ItemCat> queue = new ConcurrentLinkedQueue<ItemCat>();

	public void addItemCat(ItemCat itemCat) {
		System.out.println(itemCat.getName());
		queue.add(itemCat);
	}

	public void exectue() {
		while (true) {
			final ItemCat itemCat = queue.poll();
			if (itemCat != null) {
				boolean isParent = itemCat.getIsParent();
				if (isParent) {
					List<ItemCat> itemCats = PetClient.getInstance()
							.getItemCats("", itemCat.getCid());
					if (itemCats != null) {
						for (ItemCat itemCatVar : itemCats) {
							queue.add(itemCatVar);
						}
					}
				}
				ThreadPools.execute(new Runnable() {
					@Override
					public void run() {
						fetchItemDealwith.execute(itemCat);
					}
				});

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
