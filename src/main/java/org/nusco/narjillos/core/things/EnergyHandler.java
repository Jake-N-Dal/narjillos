package org.nusco.narjillos.core.things;

public class EnergyHandler {

    public void absorbEnergy(Energy sourceEnergy, Energy targetEnergy) {
        targetEnergy.absorb(sourceEnergy);
    }
}
