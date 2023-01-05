package com.company.environmentmodel.neuro;

// Connection class has incoming neuron and outgoing neuron and holds the synaptic weight value.
public class Connection {

    private Neuron from;
    private Neuron to;
    private double synapticWeight;
    private double synapticWeightDelta;

    public Connection(Neuron from, Neuron to) {
        this.from = from;
        this.to = to;
        this.synapticWeight = RandomGenerator.random(-2, 2);
    }

    public Neuron getFrom() {
        return from;
    }

    public Neuron getTo() {
        return to;
    }

    public double getSynapticWeight() {
        return synapticWeight;
    }

    public double getSynapticWeightDelta() {
        return synapticWeightDelta;
    }

    public void setSynapticWeightDelta(double synapticWeightDelta) {
        this.synapticWeightDelta = synapticWeightDelta;
    }

    public void updateSynapticWeight(double synapticWeight) {
        this.synapticWeight += synapticWeight;
    }
}
