package net.runelite.client.plugins.externals.oneclick.comparables.skilling;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.runelite.api.ItemID;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.plugins.externals.oneclick.comparables.ClickCompare;

public class Bones extends ClickCompare
{
	private static final Set<Integer> BONE_SET = ItemID.BUCKET;


	@Override
	public boolean isEntryValid(MenuEntry event)
	{
		return event.getOpcode() == MenuAction.GAME_OBJECT_FIRST_OPTION.getId() && !event.isForceLeftClick() &&
			event.getTarget().toLowerCase().contains("sandpit");
	}

	@Override
	public void modifyEntry(MenuEntry event)
	{
		if (findItem(BONE_SET).getLeft() == -1)
		{
			return;
		}
		MenuEntry e = event.clone();
		e.setOption("Use");
		e.setTarget("<col=ff9040>Bucket<col=ffffff> -> " + getTargetMap().get(e.getIdentifier()));
		e.setForceLeftClick(true);
		insert(e);
	}

	@Override
	public boolean isClickValid(MenuOptionClicked event)
	{
		return event.getMenuAction() == MenuAction.GAME_OBJECT_FIRST_OPTION &&
			event.getMenuTarget().contains("<col=ff9040>Bucket<col=ffffff> -> ") &&
			event.getMenuTarget().toLowerCase().contains("sandpit");
	}

	@Override
	public void modifyClick(MenuOptionClicked event)
	{
		if (updateSelectedItem(BONE_SET))
		{
			event.setMenuAction(MenuAction.ITEM_USE_ON_GAME_OBJECT);
		}
	}

	@Override
	public void backupEntryModify(MenuEntry e)
	{
		if (findItem(BONE_SET).getLeft() == -1)
		{
			return;
		}
		e.setOption("Use");
		e.setTarget("<col=ff9040>Bucket<col=ffffff> -> " + getTargetMap().get(e.getIdentifier()));
		e.setForceLeftClick(true);
	}
}
