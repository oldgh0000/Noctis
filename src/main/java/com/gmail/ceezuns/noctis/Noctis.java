package com.gmail.ceezuns.noctis;

import com.gmail.ceezuns.noctis.commands.CoordsCommand;
import com.gmail.ceezuns.noctis.listeners.ChatListener;
import com.gmail.ceezuns.noctis.listeners.EndListener;
import com.gmail.ceezuns.noctis.listeners.RepairItemListener;
import com.gmail.ceezuns.noctis.mechanics.FireworkRecipe;
import com.gmail.ceezuns.noctis.protection.ProtectionCommand;
import com.gmail.ceezuns.noctis.protection.ProtectionListener;
import com.gmail.ceezuns.noctis.protection.ProtectionManager;
import com.gmail.ceezuns.noctis.sets.SetsHeraInventoryAdapterImpl;
import com.gmail.ceezuns.noctis.sets.SetsCommand;
import com.gmail.ceezuns.noctis.sets.SetsListener;
import com.gmail.ceezuns.noctis.users.UserListener;
import com.gmail.ceezuns.noctis.users.UserManager;
import com.gmail.ceezuns.noctis.users.cobble.CobbleCommand;
import com.gmail.ceezuns.noctis.users.cobble.CobbleListener;
import com.gmail.ceezuns.noctis.users.graves.GravesCommand;
import com.gmail.ceezuns.noctis.users.graves.GravesListener;
import com.gmail.ceezuns.noctis.users.markers.MarkersCommand;
import com.gmail.ceezuns.noctis.users.nicknames.NicknameCommand;
import com.gmail.ceezuns.noctis.users.ores.OresCommand;
import com.gmail.ceezuns.noctis.users.ores.OresListener;
import com.gmail.ceezuns.noctis.users.pin.PinCommand;
import com.gmail.ceezuns.noctis.users.pin.PinListener;
import com.gmail.ceezuns.noctis.utilities.ConfigurationFile;
import com.inkzz.spigot.armorevent.ArmorListener;
import me.ceezuns.hera.Hera;
import me.ceezuns.zeus.Zeus;
import org.bukkit.plugin.java.JavaPlugin;

public final class Noctis extends JavaPlugin {

	private static Noctis instance;
	private Zeus zeus;
	private Hera hera;
	private UserManager userManager;
	private ProtectionManager protectionManager;

	@Override
	public void onEnable() {
		instance = this;
		this.zeus = new Zeus(this, new SidebarAdapterImpl());
		this.hera = new Hera(this);
		this.hera.getInventoryManager().getInventories().add(new SetsHeraInventoryAdapterImpl().getInventory());
		this.userManager = new UserManager();
		this.protectionManager = new ProtectionManager(new ConfigurationFile("protection.yml"));
		this.protectionManager.load();
		new UserListener();
		new CobbleListener();
		new GravesListener();
		new PinListener();
		new OresListener();
		new ProtectionListener();
		new MarkersCommand();
		new CobbleCommand();
		new GravesCommand();
		new PinCommand();
		new OresCommand();
		new ProtectionCommand();
		new RepairItemListener();
		new EndListener();
		new ChatListener();
		new NicknameCommand();
		new FireworkRecipe();
		new ArmorListener();
		new SetsCommand();
		new SetsListener();
		new CoordsCommand();
	}

	@Override
	public void onDisable() {
		this.protectionManager.save();
		this.userManager.clearUsers();
		this.userManager = null;
		instance = null;
	}

	public static Noctis getInstance() {
		return instance;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public ProtectionManager getProtectionManager() {
		return this.protectionManager;
	}

	public Hera getHera() {
		return hera;
	}
}
