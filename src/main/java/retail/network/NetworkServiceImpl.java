package retail.network;

import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService{

    final private NetworkRepo networkRepo;

    public NetworkServiceImpl(NetworkRepo networkRepo) {
        this.networkRepo = networkRepo;
    }

    @Override
    public Network getNetworkByNetworkName(String networkName) {
        return networkRepo.getNetworkByNetworkName(networkName);
    }
}
