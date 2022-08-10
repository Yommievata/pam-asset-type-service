package com.ikea.imc.pam.asset.type.service.service.entity;

import lombok.Builder;

import java.util.List;

@Builder
public record AssetTypeSearchParameters(
    List<Long> technicalIds,
    List<Long> classIds,
    List<Long> organisationIds
) {}
