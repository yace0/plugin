package org.geoffsplugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(
			keyName = "holdLeftArrow",
			name = "Hold Left Arrow Key",
			description = "Hold down the left arrow key when enabled"
	)
	default boolean holdLeftArrow()
	{
		return false;
	}
}
