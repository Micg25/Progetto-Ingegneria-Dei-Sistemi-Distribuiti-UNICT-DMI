package client;

import dto.AlbumDTO;

/** AlbumService is a Subject for design pattern Proxy */
public interface AlbumService {

    public AlbumDTO getAlbum(String id);

    public void createAlbum(String id, AlbumDTO dto);

    public void updateAlbum(String id, AlbumDTO dto);

}
