/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.bedmanagement;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.impl.BaseOpenmrsService;

import java.util.List;

public class BedManagementServiceImpl extends BaseOpenmrsService implements BedManagementService {

    BedManagementDAO dao;

    public void setDao(BedManagementDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<AdmissionLocation> getAllAdmissionLocations() {
        return dao.getAdmissionLocationsBy(BedManagementApiConstants.LOCATION_TAG_SUPPORTS_ADMISSION);
    }

    @Override
    public AdmissionLocation getLayoutForWard(Location location) {
        return dao.getLayoutForWard(location);
    }

    @Override
    public BedDetails assignPatientToBed(Patient patient, Bed bed) {
        return dao.assignPatientToBed(patient, bed);
    }

    @Override
    public Bed getBedById(int id) {
        return dao.getBedById(id);
    }

    @Override
    public BedDetails getBedAssignmentDetailsByPatients(Patient patient) {
        Bed bed = dao.getBedByPatient(patient);
        if(bed != null){
            Location physicalLocation = dao.getWardsForBed(bed);
            BedDetails bedDetails = new BedDetails();
            bedDetails.setBedId(bed.getId());
            bedDetails.setBedNumber(bed.getBedNumber());
            bedDetails.setPhysicalLocation(physicalLocation);
            return bedDetails;
        }
        return null;
    }
}
