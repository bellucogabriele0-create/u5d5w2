package gabrielebelluco.u5d5w2.services;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private Cloudinary cloudinaryUploader;
//    @Autowired
//    private DipendenteService dipendenteService;
}
