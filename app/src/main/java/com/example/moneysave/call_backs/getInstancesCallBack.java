package com.example.moneysave.call_backs;

import com.example.moneysave.Server.boundaries.InstanceBoundary;

import java.util.List;

public interface getInstancesCallBack extends ServerCallback{

    void Instances(List<InstanceBoundary> instanceBoundary);
}
