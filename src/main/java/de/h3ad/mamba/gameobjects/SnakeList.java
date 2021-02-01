package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class SnakeList {
    SnakeNode first;
    SnakeNode last;
    int size;
    double bodyLength;
    double maxBodyLength;


    public SnakeList(Vector3 position, double maxBodyLength) {
        first = new SnakeNode(position, null, null);
        first.position = position;
        last = first;
        this.size = 1;
        this.maxBodyLength = maxBodyLength;
        this.bodyLength = 0;
    }

    public void addNewPosition(Vector3 position) {
        SnakeNode secondNode = first.next;
        double addedDistance = position.distance(first.position);
        this.bodyLength = bodyLength + addedDistance;;
        if (secondNode != null && first.position.isOnLine(position, secondNode.position)) {
            first.position = position;
        } else {
            SnakeNode newNode = new SnakeNode(position, null, first);
            first.prev = newNode;
            first = newNode;
        }
        while (bodyLength > maxBodyLength) {
            double lastLinkLength = last.position.distance(last.prev.position);
            if (lastLinkLength >= bodyLength - maxBodyLength) {
                //update last link
                shortenLastLink(bodyLength - maxBodyLength);
            } else {
                // remove last link
                last.prev.next = null;
                last = last.prev;
            }
        }
    }

    private double getBodyLength() {
        double distance = 0;
        SnakeNode curr = first;
        while (curr.next != null) {
            distance += curr.position.distance(curr.next.position);
            curr = curr.next;
        }
        return distance;
    }

    private void shortenLastLink(double toCut) {
        if (Double.compare(last.position.getX(), last.prev.position.getX()) == 0) {
            if (last.position.getY() < last.prev.position.getY()) {
                last.position = new Vector3(last.position.getX(), last.position.getY() + toCut, last.position.getZ());
            } else {
                last.position = new Vector3(last.position.getX(), last.position.getY() - toCut, last.position.getZ());
            }
        } else {
            if (last.position.getX() < last.prev.position.getX()) {
                last.position = new Vector3(last.position.getX() + toCut, last.position.getY(), last.position.getZ());
            } else {
                last.position = new Vector3(last.position.getX() - toCut, last.position.getY(), last.position.getZ());
            }
        }
        bodyLength = this.getBodyLength() - toCut;
    }

    public List<Vector3> getAllNodes() {
        List<Vector3> body = new ArrayList<>();
        SnakeNode ref = first;
        body.add(first.position);
        while (ref.next != null) {
            body.add(ref.next.position);
            ref = ref.next;
        }
        return body;
    }


    private class SnakeNode {
        public SnakeNode prev;
        public SnakeNode next;
        public Vector3 position;

        public SnakeNode(Vector3 position, SnakeNode prev, SnakeNode next) {
            this.position = position;
            this.prev = prev;
            this.next = next;
        }
    }
}