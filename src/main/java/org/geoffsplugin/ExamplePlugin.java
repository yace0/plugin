package org.geoffsplugin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.awt.event.KeyEvent;

@Slf4j
@PluginDescriptor(
		name = "Geoff's Plugin",
		description = "Hold down left arrow key",
		tags = {"example"}
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	@Inject
	private net.runelite.client.input.KeyManager keyManager;

	private boolean holdingLeftArrow = false;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
		if (config.holdLeftArrow())
		{
			holdLeftArrowKey();
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
		releaseLeftArrowKey();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		// This method is called whenever the game state changes
		// You can use it to perform actions when the player logs in or out, for example
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}

	private void holdLeftArrowKey()
	{
		keyManager.registerKeyListener(keyListener);
	}

	private void releaseLeftArrowKey()
	{
		keyManager.unregisterKeyListener(keyListener);
	}

	private final KeyListener keyListener = new KeyListener()
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
			// Unused
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				holdingLeftArrow = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				holdingLeftArrow = false;
			}
		}
	};
}
