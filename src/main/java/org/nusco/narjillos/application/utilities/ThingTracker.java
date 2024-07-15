package org.nusco.narjillos.application.utilities;

import org.nusco.narjillos.core.geometry.Vector;
import org.nusco.narjillos.core.things.Thing;
import org.nusco.narjillos.creature.Egg;
import org.nusco.narjillos.creature.Narjillo;
import org.nusco.narjillos.experiment.environment.FoodPellet;

public class ThingTracker {

	private static final long DemoFocusTimeSec = 12;

	private static final long DemoZoomTimeSec = 3;

	private final Viewport viewport;

	private final Locator locator;

	private Thing target;

	private boolean demoMode;

	private long lastDemoTrackingTime;

	public ThingTracker(Viewport viewport, Locator locator) {
		this.viewport = viewport;
		this.locator = locator;
	}

	public synchronized void tick() {
		if (!isTracking())
			return;

		Thing currentTarget = target;

		viewport.centerOn(currentTarget);

		if (isDemoMode()) {
			if (hasBeenDemoTrackingFor(DemoFocusTimeSec + DemoZoomTimeSec))
				startTrackingRandomLivingThing();
			else if (hasBeenDemoTrackingFor(DemoFocusTimeSec))
				viewport.zoomOut();
		}

		if (currentTarget.getLabel().equals(Narjillo.LABEL)) {
			Narjillo narjillo = (Narjillo) currentTarget;
			if (narjillo.isDead()) {
				if (isDemoMode())
					startTrackingRandomLivingThing();
				else {
					Thing nextClosestNarjillo = locator.findNarjilloAt(narjillo.getPosition());
					startTracking(nextClosestNarjillo);
				}
			}
		}

		if (currentTarget.getLabel().equals(Egg.LABEL) || currentTarget.getLabel().equals(FoodPellet.LABEL)) {
			if (currentTarget.getInteractor() != Thing.NULL)
				startTracking(currentTarget.getInteractor());
		}
	}

	public synchronized void stopTracking() {
		target = null;
		demoMode = false;
	}

	public void toggleDemoMode() {
		demoMode = !demoMode;
		if (!isDemoMode())
			return;
		viewport.zoomToMaxLevel();
		startTrackingRandomLivingThing();
	}

	public String getStatus() {
		if (isDemoMode())
			return "demo";
		if (isTracking())
			return "following";
		return "freeroaming";
	}

	private synchronized boolean isTracking() {
		return target != null && target != Thing.NULL;
	}

	private boolean hasBeenDemoTrackingFor(long seconds) {
		long secondsSinceLastDemoTracking = (System.currentTimeMillis() - lastDemoTrackingTime) / 1000;
		return secondsSinceLastDemoTracking > seconds;
	}

	public void startTrackingThingAt(Vector position) {
		startTracking(locator.findThingAt(position));
	}

	public synchronized void startTracking(Thing target) {
		if (target == null || target == Thing.NULL) {
			stopTracking();
			return;
		}

		this.target = target;
		viewport.centerAndZoomOn(target);
	}

	private void startTrackingRandomLivingThing() {
		Thing target = locator.findRandomLivingThing();
		startTracking(target);
		lastDemoTrackingTime = System.currentTimeMillis();
	}

	private boolean isDemoMode() {
		return demoMode;
	}
}
