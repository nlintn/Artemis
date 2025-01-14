package de.tum.in.www1.artemis.web.rest.admin;

import static de.tum.in.www1.artemis.config.Constants.PROFILE_CORE;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tum.in.www1.artemis.domain.competency.KnowledgeArea;
import de.tum.in.www1.artemis.domain.competency.StandardizedCompetency;
import de.tum.in.www1.artemis.security.annotations.EnforceAdmin;
import de.tum.in.www1.artemis.service.competency.KnowledgeAreaService;
import de.tum.in.www1.artemis.service.competency.StandardizedCompetencyService;

/**
 * Admin REST controller for managing {@link StandardizedCompetency} entities.
 */
@Profile(PROFILE_CORE)
@RestController
@RequestMapping("api/admin/")
public class AdminStandardizedCompetencyResource {

    private static final Logger log = LoggerFactory.getLogger(AdminStandardizedCompetencyResource.class);

    private final StandardizedCompetencyService standardizedCompetencyService;

    private final KnowledgeAreaService knowledgeAreaService;

    public AdminStandardizedCompetencyResource(StandardizedCompetencyService standardizedCompetencyService, KnowledgeAreaService knowledgeAreaService) {
        this.standardizedCompetencyService = standardizedCompetencyService;
        this.knowledgeAreaService = knowledgeAreaService;
    }

    /**
     * POST api/admin/standardized-competencies : Creates a new standardized competency
     *
     * @param competency the standardized competency that should be created
     * @return the ResponseEntity with status 201 (Created) and with body containing the new standardized competency
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("standardized-competencies")
    @EnforceAdmin
    public ResponseEntity<StandardizedCompetency> createStandardizedCompetency(@RequestBody StandardizedCompetency competency) throws URISyntaxException {
        log.debug("REST request to create standardized competency : {}", competency);

        var persistedCompetency = standardizedCompetencyService.createStandardizedCompetency(competency);

        return ResponseEntity.created(new URI("/api/standardized-competencies/" + persistedCompetency.getId())).body(persistedCompetency);
    }

    /**
     * POST api/admin/standardized-competencies/knowledge-areas : Creates a new knowledge area
     *
     * @param knowledgeArea the knowledge area that should be created
     * @return the ResponseEntity with status 201 (Created) and with body containing the new knowledge area
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("standardized-competencies/knowledge-areas")
    @EnforceAdmin
    public ResponseEntity<KnowledgeArea> createKnowledgeArea(@RequestBody KnowledgeArea knowledgeArea) throws URISyntaxException {
        log.debug("REST request to create knowledge area : {}", knowledgeArea);

        var persistedKnowledgeArea = knowledgeAreaService.createKnowledgeArea(knowledgeArea);

        return ResponseEntity.created(new URI("/api/standardized-competencies/knowledge-areas" + persistedKnowledgeArea.getId())).body(persistedKnowledgeArea);
    }

}
